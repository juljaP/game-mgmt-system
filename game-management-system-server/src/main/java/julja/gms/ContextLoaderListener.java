package julja.gms;

import java.lang.reflect.Method;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import julja.gms.context.ApplicationContextListener;
import julja.util.RequestHandler;
import julja.util.RequestMapping;
import julja.util.RequestMappingHandlerMapping;


public class ContextLoaderListener implements ApplicationContextListener {

  static Logger logger = LogManager.getLogger(ContextLoaderListener.class);

  @Override
  public void contextInitailized(Map<String, Object> context) {
    try {

      ApplicationContext appCtx = new AnnotationConfigApplicationContext(AppConfig.class);
      context.put("iocContainer", appCtx);
      // printBeans(appCtx);

      ContextLoaderListener.logger.debug("------------------------------------------");

      RequestMappingHandlerMapping handlerMapper = new RequestMappingHandlerMapping();
      String[] beanNames = appCtx.getBeanNamesForAnnotation(Component.class);
      for (String beanName : beanNames) {
        Object component = appCtx.getBean(beanName);
        Method method = getRequestHandler(component.getClass());
        if (method != null) {
          RequestHandler requestHandler = new RequestHandler(method, component);
          handlerMapper.addHandler(requestHandler.getPath(), requestHandler);
        }
      }
      context.put("handlerMapper", handlerMapper);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private Method getRequestHandler(Class<?> type) {
    Method[] methods = type.getMethods();
    for (Method m : methods) {
      // @RequestMapping 붙은 메서드 조사
      RequestMapping anno = m.getAnnotation(RequestMapping.class);
      if (anno != null) {
        return m;
      }
    }
    return null;
  }

  @SuppressWarnings("unused")
  private void printBeans(ApplicationContext appCtx) {
    ContextLoaderListener.logger.info("---- Spring IoC 컨테이너에 들어있는 객체들 ----");
    String[] beanNames = appCtx.getBeanDefinitionNames();
    for (String beanName : beanNames) {
      ContextLoaderListener.logger.info(String.format("%s =======> %s\n", beanName,
          appCtx.getBean(beanName).getClass().getName()));
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}

}
