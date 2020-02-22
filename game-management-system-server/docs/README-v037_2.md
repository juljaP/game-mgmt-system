# 37_2 - Application Server : Servlet + DAO 적용 + 통신 규칙1

- Application Server 아키텍처의 구성과 특징
- 통신 프로토콜 규칙에 따라 동작하는 서버
- DBMS 연동을 위해 프로젝트에 JDBC 드라이버 추가

## 소스 및 결과

- src/main/java/julja/gms/dao/mariadb 디렉토리 생성
- src/main/java/julja/gms/dao/mariadb/BoardDaoImpl.java 추가
- src/main/java/julja/gms/dao/mariadb/GameDaoImpl.java 추가
- src/main/java/julja/gms/dao/mariadb/UserDaoImpl.java 추가
- src/main/java/julja/gms/DataLoaderListener.java 변경
- src/main/java/julja/gms/servlet/BoardListServlet.java 변경
- src/main/java/julja/gms/servlet/GameListServlet.java 변경
- src/main/java/julja/gms/servlet/UserListServlet.java 변경
- src/main/java/julja/gms/ServerApp.java 변경

## 실습  

### 1: MariaDB JDBC Driver 추가

- build.gradle 변경
- 프로젝트의 이클립스 설정 파일 갱신 
- 프로젝트에 추가되었는지 확인
  
### 2: 클라이언트 프로젝트에서 만든 DAO 관련 클래스 가져오기

- julja.gms.dao.mariadb 패키지 생성
- julja.gms.dao.mariadb.BoardDaoImpl 복사
- julja.gms.dao.mariadb.GameDaoImpl 복사
- julja.gms.dao.mariadb.UserDaoImpl 복사

### 3: Connection 객체를 준비해서 DAO를 생성할 때 주입

- julja.gms.DataLoaderListener 변경
  - Connection 객체 생성
  - mariadb 관련 DAO 객체 생성

### 4: '통신 규칙1'에 따라 동작하도록 BoardListServlet 변경

- julja.gms.servlet.Servlet 변경
  - service(Scanner in, PrintStream out) 메서드 추가
  - 기존 구현체가 영향 받지 않도록 default 로 선언
- julja.gms.servlet.BoardListServlet 변경
  - service(Scanner in, PrintStream out) 메서드 구현으로 변경
  - '통신 규칙1'에 따라 클라이언트에게 결과 응답
  - 클라이언트의 BoardListCommand 클래스의 소스 참고
  
### 5: 클라이언트의 '/board/list' 요청을 BoardListServlet으로 처리

- julja.gms.ServerApp 변경
  - 클라이언트 명령을 처리할 서블릿을 찾아 실행
  
### 6: 클라이언트의 '/user/list' 요청을 UserListServlet으로 처리

- julja.gms.servlet.UserListServlet 변경
  - 기존 service() 메서드를 service(Scanner in, PrintStream out)으로 변경
  - '통신 규칙1'에 따라 응답하도록 변경
- julja.gms.ServerApp 변경
  - '/user/list' 요청을 처리할 UserListServlet을 서블릿맵에 등록
  
### 7: 클라이언트의 '/game/list' 요청을 GameListServlet으로 처리

- julja.gms.servlet.GameListServlet 변경
  - 기존 service() 메서드를 service(Scanner in, PrintStream out)으로 변경
  - '통신 규칙1'에 따라 응답하도록 변경
- julja.gms.ServerApp 변경
  - '/game/list' 요청을 처리할 GameListServlet을 서블릿맵에 등록