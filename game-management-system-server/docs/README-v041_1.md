# 41_1 - Connection Pool 

- Pooling 
- Connection Pool

### Pooling 

- 객체 사용한 후에 버리지 않고 보관하여, 객체 재사용 하는 것.
- 객체가 필요할 때 마다 빌려서 쓰고, 쓰고 난 후에는 반납하는 개념.
- 이점:
  - 객체 생성에 소요되는 시간을 줄일 수 있다.
  - 가비지 생성을 억제하기 때문에 메모리 낭비 줄일 수 있다.
- 예:
  - DB 커넥션 풀, 스레드 풀 등
- GoF의 'Flyweight' 디자인 패턴과 유사하다.

## 실습 소스 및 결과

- src/main/java/julja/sql/ConnectionFactory.java 삭제
- src/main/java/julja/sql/DataSource.java 추가
- src/main/java/julja/sql/PlatformTransactionManager.java 변경
- src/main/java/julja/gms/dao/mariadb/XxxDaoImpl.java 변경
- src/main/java/julja/gms/DataLoaderListener.java 변경
- src/main/java/julja/gms/ServerApp.java 변경


### 1: DB 커넥션 풀 객체 생성

- julja.sql.DataSource 추가
  - ConnectionFactory -> DataSource로 이름 변경
  - JDBC API와 같이 커넥션 객체 생성하고 관리하는 역할자 DataSource로 정의
  - ConnectionFactory + Pooling 기능 = DataSource
  
### 2: PlatformTransactionManager 변경

- julja.sql.PlatformTransactionManager 변경
  - ConnectionFactory 대신 DataSource 사용하도록 변경

### 3: DataSource 사용하도록 DAO 변경

- julja.gms.dao.mariadb.XxxDaoImpl 변경
  - ConnectionFactory 대신 DataSource 사용하도록 변경
  
### 4: DataSource DAO에 주입

- julja.gms.DataLoaderListener 변경
  - ConnectionFactory 대신 DataSource 객체 생성
  - DAO에 DataSource 주입
  - 애플리케이션이 종료될 때 모든 DB 커넥션을 닫는다.
  
### 5: 클라이언트 요청을 처리한 후 Connection을 닫지 말고 반납

- julja.gms.ServerApp 변경
  - 클라이언트에게 응답한 후 스레드에서 커넥션 객체 제거
  - 제거된 커넥션 객체는 재사용하기 위해 닫지 않는다.
  
### 7: /photoboard/add, /photoboard/update, /photoboard/delete을 테스트 