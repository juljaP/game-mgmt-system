# 51_1 - Spring IoC 컨테이너와 MyBatis 프레임워크 연동

- Spring IoC 컨테이너와 Mybatis 프레임워크 연동

## 소스 및 결과

- build.gradle 변경
- src/main/java/julja/sql/PlatformTransactionManager.java 삭제
- src/main/java/julja/sql/TransactionTemplate.java 삭제
- src/main/java/julja/sql/TransactionCallback.java 삭제
- src/main/java/julja/sql/SqlSessionFactoryProxy.java 삭제
- src/main/java/julja/sql/SqlSessionProxy.java 삭제
- src/main/java/julja/sql/MybatisDaoFactory.java 삭제
- src/main/resources/julja/gms/conf/mybatis-config.xml 삭제
- src/main/java/julja/gms/service/impl/PhotoBoardServiceImpl.java 변경
- src/main/java/julja/gms/AppConfig.java 변경
- src/main/java/julja/gms/ServerApp.java 변경


### 1: Mybatis를 Spring IoC 컨테이너와 연결할 때 사용할 의존 라이브러리를 가져오기

- Spring IoC 컨테이너의 라이브러리 정보 찾기
  - `mvnrepository.com` 또는 `search.maven.org`에서 라이브러리 찾기
  - `mybatis-spring`, `spring-jdbc` 라이브러리 검색 
- `build.gradle` 변경
- Gradle 빌드 도구를 사용하여 라이브러리 파일을 프로젝트로 가져오기

### 2: Mybatis에서 관리했던 DB 커넥션풀(DataSource)을 Spring IoC 컨테이너로 옮긴다.

- julja.gms.AppConfig 변경
  - DataSource 객체를 리턴하는 팩토리 메서드를 추가
  
### 3: PlatformTransactionManager를 Spring 것으로 교체

- julja.sql.PlatformTransactionManager 삭제
- julja.sql.TransactionTemplate 삭제
- julja.sql.TransactionCallback 삭제
- julja.gms.AppConfig 변경
  - 기존의 PlatformTransactionManager를 생성하는 메서드를 변경
  - transactionManager()를 변경  

### 4: 기존의 서비스 객체의 트랜잭션 관리자를 Spring의 것으로 교체

- julja.gms.service.impl.PhotoBoardServiceImpl 변경
  - 트랜잭션 관련 클래스를 Spring의 것으로 교체
  - 트랜잭션을 다루는 코드를 Spring 사용법에 따라 변경
  
### 5: Mybatis의 SqlSessionFactory를 Spring IoC 컨테이너 용으로 준비

- julja.gms.AppConfig 변경
  - `mybatis-spring` 라이브러리에서 제공하는 어댑터를 사용하여 SqlSessionFactory를 교체 
  - sqlSessionFactory()를 변경
- julja.gms.conf.mybatis-config.xml 삭제
- julja.sql.SqlSessionFactoryProxy 삭제
- julja.sql.SqlSessionProxy 삭제

### 6: DAO 생성기를 Mybatis-Spring 어댑터로 교체

- julja.gms.AppConfig 변경
  - @MapperScan 애노테이션으로 설정
  - 수동으로 DAO 객체를 만드는 팩토리 메서드를 모두 제거
  - MybatisDaoFactory 객체를 생성하는 팩토리 메서드를 삭제
- julja.sql.MybatisDaoFactory 삭제
- julja.gms.ServerApp 변경
  - SqlSessionFactory 사용 코드를 제거
  - 트랜잭션은 Spring 프레임워크에서 관리