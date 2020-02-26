# 39_2 - Connection 개별화: Factory Method 패턴 커넥션 객체 생성


- Factory Method 디자인 패턴
  - 객체 생성 과정이 복잡할 경우에 사용하는 설계 기법
  - new 연산자 이용하여 직접 객체 생성하는 대신에 메서드 통해 리턴 

## 소스 및 결과

- src/main/java/julja/util/ConnectionFactory.java 추가
- src/main/java/julja/gms/DataLoaderListener.java 변경
- src/main/java/julja/gms/dao/mariadb/XxxDaoImpl.java 변경

### 1: 커넥션 생성시 팩토리 메서드 사용

Connection 객체는 DriverManager 통해 생성하지만 생성 방법이 바뀔 수 있음.
Connection 객체 생성 방법이 바뀌면, DAO 구현체 모두 변경해야 하기 때문에 커넥션 객체 생성을 별도의 클래스에 맡김.

- julja.util.ConnectionFactory 추가
  - Connection 객체 생성하는 메서드 추가
- julja.gms.DataLoaderListener 변경
  - ConnectionFactory 객체 준비
  - DAO 구현체에 ConnectionFactory 객체 주입
- julja.gms.dao.mariadb.XxxDaoImpl 변경
  - 생성자에서 ConnectionFactory 객체 받기
  - 직접 Connection 객체 생성하는 대신에 
  ConnectionFactory 객체 통해 Connection 얻는다.


### 메서드 마다 커넥션을 구분하는 방식의 문제점

- 메서드 마다 별도의 커넥션을 사용
- 따라서 PhotoBoardDao의 insert()와 PhotoFileDao의 insert()를 한 단위 작업으로 묶을 수 없다.
- 즉 사진 게시글 입력과 첨부 파일 입력을 한 단위의 작업으로 다룰 수 없다.
- 트랜잭션을 구현할 수 없다. 
  