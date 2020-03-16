package julja.util;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.io.Resources;

// - 클래스를 찾아 객체 생성
// - 의존 객체를 주입
// - 객체 생성과 소멸 관리

public class ApplicationContext {

  // 클래스 이름을 담을 저장소
  ArrayList<String> classNames = new ArrayList<>();

  // concrete class를 담을 저장소
  ArrayList<Class<?>> componentClasses = new ArrayList<>();

  // 객체 저장소
  HashMap<String, Object> objPool = new HashMap<>();

  public ApplicationContext(String packageName, Map<String, Object> beans) throws Exception {
    // Map에 들어 있는 객체를 먼저 객체풀에 보관
    Set<String> keySet = beans.keySet();
    for (String key : keySet) {
      objPool.put(key, beans.get(key));
    }

    // 패키지의 클래스를 찾아 인스턴스 만들고 보관

    // => 패키지의 실제 파일 시스템 경로
    // 패키지명을 파일 시스템 경로로 바꿔서 전달
    File path = Resources.getResourceAsFile(packageName.replace('.', '/'));

    // => 해당 경로를 뒤져서 모든 클래스의 이름을 알아낸다.
    findClasses(path, packageName);

    // => 클래스 이름으로 클래스 로딩
    // => @Component 애노테이션이 붙은 클래스를 별도의 목록으로 준비
    prepareComponentClasses();

    // => concrete class의 객체 생성
    // => concrete class의 생성자를 호출할 때 의존 객체 주입
    // => 의존 객체는 객체풀에서 찾아 주입
    // => 객체풀에 의존 객체가 없으면 생성하여 주입
    for (Class<?> clazz : componentClasses) {
      try {
        createInstance(clazz);
      } catch (Exception e) {
        System.out.printf("%s 클래스의 객체를 생성할 수 없습니다.\n", clazz.getName());
      }
    }

  }

  public void printBeans() {
    System.out.println("-----------------------------------");
    Set<String> beanNames = objPool.keySet();
    for (String beanName : beanNames) {
      System.out.printf("%s =====> %s\n", //
          beanName, // 객체 이름
          objPool.get(beanName).getClass().getName() // 클래스명
      );
    }
  }

  // 객체를 한 개 등록
  public void addBean(String name, Object bean) {
    objPool.put(name, bean);
  }

  // 객체 이름으로 객체를 찾아 꺼내줌
  public Object getBean(String name) {
    return objPool.get(name);
  }

  private void prepareComponentClasses() throws Exception {
    // 클래스 이름으로 객체 생성
    for (String className : classNames) {

      // 클래스 이름으로 클래스 정보 가져옴
      Class<?> clazz = Class.forName(className);
      if (!isComponentClass(clazz)) {
        continue; // 객체를 생성할 수 없는 경우 건너 뛴다.
      }

      componentClasses.add(clazz);
    }
  }

  private Object createInstance(Class<?> clazz) throws Exception {

    // 클래스의 생성자 정보를 알아냄
    Constructor<?> constructor = clazz.getConstructors()[0];

    // 생성자의 파라미터 정보를 알아냄
    Parameter[] params = constructor.getParameters();

    // 생성자에 넘겨 줄 파라미터 객체 준비
    ArrayList<Object> values = new ArrayList<>();
    for (Parameter param : params) {
      values.add(getParameterInstance(param));
    }

    // 생성자를 호출하여 객체 준비
    Object obj = constructor.newInstance(values.toArray());
    // System.out.printf("%s 객체를 생성하였음!\n", clazz.getSimpleName());

    // 생성된 객체는 객체풀에 보관
    objPool.put(getBeanName(clazz), obj);
    return obj;
  }

  private String getBeanName(Class<?> clazz) {
    Component compAnno = clazz.getAnnotation(Component.class);
    if (compAnno == null) {
      // @Component 어노테이션 없거나 이름 지정하지 않으면 클래스 이름을 빈 이름으로 사용
      return clazz.getName();
    }
    return compAnno.value();
  }

  private Object getParameterInstance(Parameter param) throws Exception {
    Collection<?> objs = objPool.values();

    // 먼저 객체 보관소에 파라미터 객체가 있는지 검사
    for (Object obj : objs) {
      // 있으면, 같은 객체를 또 만들지 않고 기존의 생성된 객체 리턴
      if (param.getType().isInstance(obj)) {
        return obj;
      }
    }

    // 없으면, 파라미터 객체 생성
    // => 단, 현재 클래스 이름으로 등록된 객체에 대해서만 파라미터 객체를 생성할 수 있음
    Class<?> clazz = findParameterClassInfo(param.getType());
    if (clazz == null) {
      // 파라미터에 해당하는 적절한 클래스를 찾지 못했으면 파라미터 객체를 생성할 수 없음
      return null;
    }
    return createInstance(clazz);
  }

  private Class<?> findParameterClassInfo(Class<?> paramType) throws Exception {
    // concrete class 목록에서 파라미터에 해당하는 클래스가 있는지 조사
    for (Class<?> clazz : componentClasses) {
      if (paramType.isInterface()) {
        // 파라미터가 인터페이스라면 각각의 클래스에 대해 그 인터페이스를 구현했는지 검사
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> interfaceInfo : interfaces) {
          if (interfaceInfo == paramType) {
            return clazz;
          }
        }
      } else if (isType(clazz, paramType)) {
        // 파라미터가 클래스라면, 각각의 클래스에 대해 같은 타입이거나 수퍼 클래스인지 검사
        return clazz;
      }
    }

    // 파라미터에 해당하는 타입이 concrete class 목록에 없다면, null 리턴
    return null;
  }

  private boolean isType(Class<?> clazz, Class<?> target) {
    // 수퍼 클래스로 따라 올라가면서 같은 타입인지 검사

    if (clazz == target) {
      return true;
    }

    if (clazz == Object.class) {
      // 더 이상 상위 클래스가 없다면,
      return false;
    }

    return isType(clazz.getSuperclass(), target);
  }

  private boolean isComponentClass(Class<?> clazz) {
    if (clazz.isInterface() // 인터페이스인 경우
        || clazz.isEnum() // Enum 타입인 경우
        || Modifier.isAbstract(clazz.getModifiers()) // 추상 클래스인 경우
    ) {
      return false; // 객체 생성 불가
    }
    Component compAnno = clazz.getAnnotation(Component.class);
    if (compAnno == null) {
      return false;
    }
    return true;
  }


  private void findClasses(File path, String packageName) {

    File[] files = path.listFiles(new FileFilter() {
      @Override
      public boolean accept(File file) {
        if (file.isDirectory() //
            || (file.getName().endsWith(".class")//
                && !file.getName().contains("$")))
          return true;
        return false;
      }
    });
    for (File f : files) {
      String classOrPackageName = //
          packageName + "." + f.getName().replace(".class", "");
      if (f.isFile()) {
        // System.out.println("ApplicationContext: " + classOrPackageName);
        classNames.add(classOrPackageName);
      } else {
        findClasses(f, classOrPackageName);
      }
    }

  }

}
