# 40_3 - localConnection + transaction

- ConnectionFactory 통해 얻은 Connection 객체 가지고 트랜잭션 다루기

### 메서드 별로 커넥션 개별화 한 상태에서 트랜잭션 적용

## 소스 및 결과

- src/main/java/julja/gms/servlet/PhotoBoardAddServlet.java 변경
- src/main/java/julja/gms/servlet/PhotoBoardUpdateServlet.java 변경
- src/main/java/julja/gms/servlet/PhotoBoardDeleteServlet.java 변경
- src/main/java/julja/gms/ServerApp.java 변경

### 1: PhotoBoardAddServlet에 트랜잭션 적용

- julja.gms.servlet.PhotoBoardAddServlet 변경
  - ConnectionFactory 주입 받기.
  - ConnectionFactory 통해 Connection 얻은 후에 트랜잭션 제어.

### 2: PhotoBoardUpdateServlet에 트랜잭션 적용

- julja.gms.servlet.PhotoBoardUpdateServlet 변경
  - ConnectionFactory 주입 받기.
  - ConnectionFactory 통해 Connection 얻은 후에 트랜잭션 제어.
  
### 3: PhotoBoardDeleteServlet에 트랜잭션 적용

- julja.gms.servlet.PhotoBoardDeleteServlet 변경
  - ConnectionFactory 주입 받기.
  - ConnectionFactory 통해 Connection 얻은 후에 트랜잭션 제어.

### 4: 트랜잭션 다뤄야 하는 서블릿 객체에 ConnectionFactory 주입

- julja.gms.ServerApp 변경
  - PhotoBoardAddServlet, PhotoBoardUpdateServlet, PhotoBoardDeleteServlet 객체에 ConectionFactory 주입.

### 5: /photoboard/add, /photoboard/update, /photoboard/delete 테스트