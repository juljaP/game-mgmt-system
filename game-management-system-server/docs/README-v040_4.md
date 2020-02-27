# 40_4 - Connection 스레드 보관: 트랜잭션 관리자 도입

- Connection을 제어하여 트랜잭션을 관리하는 코드 캡슐화

## 소스 및 결과

- src/main/java/julja/sql/PlatformTransactionManager.java 추가
- src/main/java/julja/gms/servlet/PhotoBoardAddServlet.java 변경
- src/main/java/julja/gms/servlet/PhotoBoardUpdateServlet.java 변경
- src/main/java/julja/gms/servlet/PhotoBoardDeleteServlet.java 변경
- src/main/java/julja/gms/DataLoaderListener.java 변경
- src/main/java/julja/gms/ServerApp.java 변경


### 1: 트랜잭션 제어 코드를 캡슐화 

- julja.sql.PlatformTransactionManager 추가
  - 트랜잭션을 시작시키고, 커밋/롤백하는 메서드를 정의
  
  
### 2: PhotoBoardAddServlet에 트랜잭션 관리자 적용

- julja.gms.servlet.PhotoBoardAddServlet 변경
  - PlatformTransactionManager를 주입 받기
  - 트랜잭션 관리자를 통해 트랜잭션 제어
  
### 3: PhotoBoardUpdateServlet에 트랜잭션 관리자 적용

- julja.gms.servlet.PhotoBoardUpdateServlet 변경
  - PlatformTransactionManager를 주입 받기
  - 트랜잭션 관리자를 통해 트랜잭션 제어
  
### 4: PhotoBoardDeleteServlet에 트랜잭션 관리자 적용

- julja.gms.servlet.PhotoBoardDeleteServlet 변경
  - PlatformTransactionManager를 주입 받기
  - 트랜잭션 관리자를 통해 트랜잭션 제어

### 5: DataLoaderListener에서 트랜잭션 관리자 준비

- julja.gms.DataLoaderListener 변경
  - PlatformTransactionManager 객체 준비

### 6: 트랜잭션 관리자를 서블릿에 주입

- julja.gms.ServerApp 변경
  - 맵에서 PlatformTransactionManager 객체를 꺼내 서블릿 객체에 주입
  
### 7: /photoboard/add, /photoboard/update, /photoboard/delete 테스트