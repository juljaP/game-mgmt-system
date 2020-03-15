# 43_2 - MyBatis + 트랜잭션 적용

- Mybatis로 트랜잭션 제어

## 소스 및 결과

- src/main/java/julja/sql/SqlSessionProxy.java 추가
- src/main/java/julja/sql/SqlSessionFactoryProxy.java 추가
- src/main/java/julja/sql/PlatformTransactionManager.java 변경
- src/main/java/julja/sql/DataSource.java 삭제
- src/main/java/julja/sql/ConnectionProxy.java 삭제
- src/main/java/julja/gms/DataLoaderListener.java 변경
- src/main/java/julja/gms/ServerApp.java 변경
- src/main/java/julja/gms/dao/mariadb/BoardDaoImpl.java 변경
- src/main/java/julja/gms/dao/mariadb/LessonDaoImpl.java 변경
- src/main/java/julja/gms/dao/mariadb/MemberDaoImpl.java 변경
- src/main/java/julja/gms/dao/mariadb/PhotoBoardDaoImpl.java 변경
- src/main/java/julja/gms/dao/mariadb/PhotoFileDaoImpl.java 변경

### 1: SqlSession 프록시 

- julja.sql.SqlSessionProxy 클래스 추가
  - close() 호출할 때 닫지 않도록 오버라이딩 
  
### 2: SqlSessionFactory 프록시

- julja.sql.SqlSessionFactoryProxy 클래스 추가
  - 생성한 SqlSession 객체 스레드에 보관하기 위해 ThreadLocal 필드 추가
  - openSession(boolean) 메서드 변경

### 3: PlatformTransactionManager 변경

- julja.sql.PlatformTransactionManager 변경
  - Connection 대신 SqlSession 다루도록 변경 
- julja.sql.DataLoaderListener 변경
  - PlatformTransactionManager 준비할 때 DataSource 대신 SqlSessionFactory 주입
- julja.sql.DataSource 삭제
- julja.sql.ConnectionProxy 삭제

### 4: 스레드 작업 종료했 때 SqlSession 닫기

- julja.gms.ServerApp 변경

### 5: DAO에서 commit()/rollback() 제거

- julja.gms.dao.mariadb.*DaoImpl 변경
  - commit()/rollback() 호출 코드 제거
