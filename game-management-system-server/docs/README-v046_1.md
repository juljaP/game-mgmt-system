# 46_1 - IoC 컨테이너

- IoC 컨테이너의 개념과 구동 원리 이해
- 리플랙션 API 활용하여 클래스 정보 다루고 객체 생성

### IoC(Inversion Of Control)

- '제어의 역전'

- 예1) 의존 객체 생성
  - 보통의 실행 흐름은 객체 사용하는 쪽에서 그 객체 만드는 것
  - 시스템 구조가 복잡할 경우에는 사용할 객체 외부에서 주입받는 것이 유지보수에 유리
  
- 예2) 메서드 호출
  - 개발자가 작성한 코드 흐름에 따라 호출하는 것이 아닌, 특정 상태에 놓여졌 때 뒤에서 자동으로 호출하는 방식
  - 보통 이런 메서드 '이벤트 핸들러', '이벤트 리스너'라 부른다.
  - 또는 시스템 쪽에서 호출하는 메서드라는 의미로 '콜백(callback) 메서드'라고 부르기도 

### IoC 컨테이너

- 개발자가 직접 객체 생성하는 것이 아닌, 객체 생성 전담하는 역할자 통해 객체 준비
- 이 역할자 '빈 컨테이너(bean container)'라고 부른다.
- 여기에 객체가 사용할 의존 객체 자동으로 주입하는 역할 추가
- 즉 객체 스스로 자신이 사용할 객체 만드는 것이 아니라, 외부의 빈 컨테이너로부터 의존 객체 주입받는 것
- IoC 컨테이너 = 빈 컨테이너 + 의존 객체 주입 => Spring IoC 컨테이너


## 소스 및 결과

- src/main/java/julja/util/Component.java 추가
- src/main/java/julja/util/ApplicationContext.java 추가
- src/main/java/julja/lms/service/impl/BoardServiceImpl2.java 삭제
- src/main/java/julja/lms/service/impl/XxxServiceImpl.java 변경
- src/main/java/julja/lms/servlet/XxxServlet.java 변경
- src/main/java/julja/lms/DataLoaderListener.java 의 이름 변경
  - ContextLoaderListener.java 로 이름 변경
  
  
### 1: IoC 컨테이너 클래스 준비

- julja.util.ApplicationContext 클래스 생성

### 2: 특정 패키지에 소속된 클래스 이름 수집

- julja.util.ApplicationContext 클래스 변경
  - 패키지명 입력받아서 해당 패키지 뒤져 모든 클래스의 이름을 가져온다.
- julja.lms.DataLoaderListener의 이름 변경
  - 애플리케이션 실행할 때 사용할 객체나 환경 준비->'ContextLoaderListener'라 변경
- julja.lms.ContextLoaderListener 변경
  - ApplicationContext 객체 생성하여 맵에 보관
  
### 3: 객체 생성할 수 있는 concrete class만 추출
  
- julja.util.ApplicationContext 클래스 변경
  - reflection API 사용하여 인터페이스와 추상클래스 등 구분
  
### 4: concrete class의 생성자 정보 알아낸다
  
- julja.util.ApplicationContext 클래스 변경
  - reflection API 사용하여 클래스의 생성자 알아낸다.
  - 생성자의 파라미터 정보 알아낸다.
  
### 5: concrete class의 생성자 호출하여 객체 준비
  
- julja.util.ApplicationContext 클래스 변경
  - concrete class만 따로 로딩하여 목록 관리
  - reflection API 사용하여 생성자의 파라미터 정보를 알아낸다.
  - 파라미터 객체 준비하여 생성자 호출
  - 생성된 객체 객체 보관소(objPool)에 저장

### 6: 애노테이션 이용하여 생성된 객체의 이름 관리

- julja.util.Component 애노테이션 추가
  - 빈의 이름 설정하는 애노테이션 정의
- julja.lms.servlet.XxxServlet 변경
  - 클래스에 Component 애노테이션 적용하여 이름 지정
- julja.util.ApplicationContext 클래스 변경
  - 객체 객체풀에 저장할 때 Component 애노테이션에서 이름 가져와서 저장
  - Component 애노테이션이 없으면 그냥 클래스 이름으로 저장
  - 외부에서 생성한 객체 저장할 수 있도록 생성자 변경
  - 외부에서 저장된 객체 꺼낼 수 있도록 getBean() 메서드 추가
- julja.util.ApplicationContext 클래스 변경
  - 외부에서 생성한 객체 등록한 addBean() 메서드 추가
  - 내부에서 생성한 객체 꺼낼 수 있도록 getBean() 메서드 추가
- julja.lms.ServerApp 변경
  - ApplicationContext 사용하여 객체 관리
  
### 7: @Component 애노테이션이 붙은 객체만 관리

- julja.lms.servlet.impl.XxxServiceImpl 변경
  - 클래스에 Component 애노테이션 적용
- julja.util.ApplicationContext 클래스 변경
  - @Component가 붙은 클래스만 찾아내 객체 생성
  - 내부에 보관된 객체 정보 출력하는 printBeans() 추가 
- julja.ContextLoaderListener 변경
  - ApplcationContext 생성한 후 printBeans() 호출하여 보관된 객체 정보 조회
  
### 8: IoC 컨테이너의 이점 활용

- julja.lms.servlet.HelloServlet 추가
  - 클라이언트가 "/hello" 요청했 때 "안녕하세요!"하고 인사말 응답
  - IoC 컨테이너 도입하면, 새 명령 처리하는 서블릿이 추가되더라도 기존 코드(예: ServerApp) 변경할 필요 없음
  
### 9: IoC 컨테이너의 이점 활용 II

- julja.lms.servlet.HelloServlet 삭제
  - 기존 코드 손댈 필요 없음
  
  
  