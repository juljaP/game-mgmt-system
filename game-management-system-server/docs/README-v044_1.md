# 44_1 - UI 객체에서 비즈니스 로직 분리

비즈니스 로직 별도의 클래스로 분리하면, UI 구현 방식이 변경되더라도 비즈니스 로직 재사용할 수 있다.

- Presentation/Service(Business)/Persistence Layer

### Presentation Layer
- UI 담당

### Business(Service) Layer
- 업무 로직, 트랜잭션 제어 담당

### Persistence Layer
- 데이터 저장 담당


## 소스 및 결과

- src/main/java/julja/gms/service/BoardService.java 추가
- src/main/java/julja/gms/service/GameService.java 추가
- src/main/java/julja/gms/service/UserService.java 추가
- src/main/java/julja/gms/service/PhotoBoardService.java 추가
- src/main/java/julja/gms/service/impl/BoardServiceImpl.java 추가
- src/main/java/julja/gms/service/impl/GameServiceImpl.java 추가
- src/main/java/julja/gms/service/impl/UserServiceImpl.java 추가
- src/main/java/julja/gms/service/impl/PhotoBoardServiceImpl.java 추가
- src/main/java/julja/gms/ServerApp.java 변경
- src/main/java/julja/gms/DataLoaderListener.java 변경
- src/main/java/julja/gms/servlet/XxxServlet.java 변경


### 1: PhotoBoardXxxServlet 에서 비즈니스 로직 분리

- julja.gms.service 패키지 추가
- julja.gms.service.PhotoBoardService 인터페이스 추가
- julja.gms.service.GameService 인터페이스 추가
- julja.gms.service.impl.PhotoBoardServiceImpl 클래스 추가
- julja.gms.service.impl.GameServiceImpl 클래스 추가
- julja.gms.servlet.PhotoBoardXxxServlet 변경
  - 비즈니스 로직과 트랜잭션 제어 코드 서비스 객체로 옮김
- julja.gms.DataLoaderListener 변경
  - 서비스 객체 생성
- julja.gms.ServerApp 변경
  - 서블릿에 서비스 객체 주입
  
### 2: BoardXxxServlet 에서 비즈니스 로직 분리

- julja.gms.service.BoardService 인터페이스 추가
- julja.gms.service.impl.BoardServiceImpl 클래스 추가
- julja.gms.servlet.BoardXxxServlet 변경
  - 비즈니스 로직과 트랜잭션 제어 코드 서비스 객체로 옮김
- julja.gms.DataLoaderListener 변경
  - 서비스 객체 생성
- julja.gms.ServerApp 변경
  - 서블릿에 서비스 객체 주입
  
### 3: UserXxxServlet 에서 비즈니스 로직 분리

- julja.gms.service.UserService 인터페이스 추가
- julja.gms.service.impl.UserServiceImpl 클래스 추가
- julja.gms.servlet.UserXxxServlet 변경
  - 비즈니스 로직과 트랜잭션 제어 코드 서비스 객체로 옮김
- julja.gms.servlet.LoginServlet 변경
  - 비즈니스 로직과 트랜잭션 제어 코드 서비스 객체로 옮김
- julja.gms.DataLoaderListener 변경
  - 서비스 객체 생성
- julja.gms.ServerApp 변경
  - 서블릿에 서비스 객체 주입
  
### 4: GameXxxServlet 에서 비즈니스 로직 분리

- julja.gms.service.GameService 인터페이스 변경
- julja.gms.service.impl.GameServiceImpl 클래스 변경
- julja.gms.servlet.GameXxxServlet 변경
  - 비즈니스 로직과 트랜잭션 제어 코드 서비스 객체로 옮김
- julja.gms.DataLoaderListener 변경
  - 서비스 객체 생성
- julja.gms.ServerApp 변경
  - 서블릿에 서비스 객체 주입