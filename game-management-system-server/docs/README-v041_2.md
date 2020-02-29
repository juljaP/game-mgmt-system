# 41_2 - 트랜잭션 관리자를 사용하는 코드 캡슐화

- 반복되는 코드를 캡슐화하여 코드 단순화

## 소스 및 결과

- src/main/java/julja/sql/ConnectionFactory.java 삭제
- src/main/java/julja/sql/DataSource.java 추가
- src/main/java/julja/sql/PlatformTransactionManager.java 변경
- src/main/java/julja/gms/dao/mariadb/XxxDaoImpl.java 변경
- src/main/java/julja/gms/DataLoaderListener.java 변경
- src/main/java/julja/gms/ServerApp.java 변경

### 1: 트랜잭션 관리자를 사용하는 코드를 캡슐화하여 별도의 클래스로 분리

- julja.sql.TransactionTemplate 추가
  - 트랜잭션 관리자를 사용하는 코드를 메서드로 정의
- julja.sql.TransactionCallback 인터페이스 추가
  - TransactionTemplate이 작업을 실행할 때 호출할 메서드 규칙을 정의
  - 트랜잭션 작업은 이 규칙에 따라 작성해야 함
  
