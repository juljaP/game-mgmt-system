# 43_1 - MyBatis SQL 맵퍼 프레임워크 사용하여 JDBC 코드 대체

- Mybatis SQL 맵퍼의 특징과 동작 원리 이해
- Mybatis 퍼시스턴스 프레임워크 설정하고 사용

## 소스 및 결과

- build.gradle 변경
- src/main/java/julja/gms/domain/PhotoBoard.java 변경
- src/main/java/julja/gms/dao/mariadb/BoardDaoImpl.java 변경
- src/main/java/julja/gms/dao/mariadb/GameDaoImpl.java 변경
- src/main/java/julja/gms/dao/mariadb/UserDaoImpl.java 변경
- src/main/java/julja/gms/dao/mariadb/PhotoBoardDaoImpl.java 변경
- src/main/java/julja/gms/dao/mariadb/PhotoFileDaoImpl.java 변경
- src/main/java/julja/gms/servlet/PhotoBoardDetailServlet.java 변경
- src/main/java/julja/gms/DataLoaderListener.java 변경
- src/main/java/julja/gms/ServerApp.java 변경
- src/main/resources/julja/gms/conf/mybatis-config.xml 추가
- src/main/resources/julja/gms/conf/jdbc.properties 추가
- src/main/resources/julja/gms/mapper/BoardMapper.xml 추가
- src/main/resources/julja/gms/mapper/GameMapper.xml 추가
- src/main/resources/julja/gms/mapper/UserMapper.xml 추가
- src/main/resources/julja/gms/mapper/PhotoBoardMapper.xml 추가
- src/main/resources/julja/gms/mapper/PhotoFileMapper.xml 추가

### 1: 프로젝트에 MyBatis 라이브러리 추가

- build.gradle   
  - 의존 라이브러리 블록에서 `mybatis` 라이브러리 등록
- gradle 이용하여 eclipse 설정 파일 갱신
  - `$ gradle eclipse`
- 이클립스에서 프로젝트 갱신
  
### 2: `MyBatis` 설정 파일 준비

- julja/gms/conf/jdbc.properties
    - `MyBatis` 설정 파일에서 참고할 DBMS 접속 정보 등록
- julja/gms/conf/mybatis-config.xml
    - `MyBatis` 설정 파일이다.
    - DBMS 서버의 접속 정보 갖고 있는 jdbc.properties 파일의 경로 등록
    - DBMS 서버 정보 설정
    - DB 커넥션 풀 설정

### 3: BoardDaoImpl 에 Mybatis 적용

- julja.gms.dao.mariadb.BoardDaoImpl 클래스 변경
  - SQL 뜯어내어 BoardMapper.xml로 옮기기
  - JDBC 코드 뜯어내고 그 자리에 Mybatis 클래스로 대체
- julja/gms/mapper/BoardMapper.xml 추가
  - BoardDaoImpl 에 있던 SQL문 이 파일로 옮기기
- julja/gms/conf/mybatis-config.xml 변경 
  - BoardMapper 파일의 경로 등록
- julja.gms.DataLoaderListener 변경
  - SqlSessionFactory 객체 준비
  - BoardDaoImpl 에 주입

### 4: UserDaoImpl 에 Mybatis 적용

- julja.gms.dao.mariadb.UserDaoImpl 클래스 변경
  - SQL 뜯어내어 UserMapper.xml로 옮기기
  - JDBC 코드 뜯어내고 그 자리에 Mybatis 클래스로 대체
- julja/gms/mapper/UserMapper.xml 추가
  - UserDaoImpl 에 있던 SQL문 이 파일로 옮기기
- julja/gms/conf/mybatis-config.xml 변경 
  - UserMapper 파일의 경로 등록
- julja.gms.DataLoaderListener 변경
  - SqlSessionFactory 객체 준비
  - UserDaoImpl 에 주입

### 5: GameDaoImpl 에 Mybatis 적용

- julja.gms.dao.mariadb.GameDaoImpl 클래스 변경
  - SQL 뜯어내어 GameMapper.xml로 옮기기
  - JDBC 코드 뜯어내고 그 자리에 Mybatis 클래스로 대체
- julja/gms/mapper/GameMapper.xml 추가
  - GameDaoImpl 에 있던 SQL문 이 파일로 옮기기
- julja/gms/conf/mybatis-config.xml 변경 
  - GameMapper 파일의 경로 등록
- julja.gms.DataLoaderListener 변경
  - SqlSessionFactory 객체 준비
  - GameDaoImpl 에 주입

### 6: PhotoBoardDaoImpl 에 Mybatis 적용

- julja.gms.domain.PhotoBoard 클래스 변경
  - PhotoFile 목록 필드 추가
- julja.gms.dao.mariadb.PhotoBoardDaoImpl 클래스 변경
  - SQL 뜯어내어 PhotoBoardMapper.xml로 옮기기
  - JDBC 코드 뜯어내고 그 자리에 Mybatis 클래스로 대체
- julja/gms/mapper/PhotoBoardMapper.xml 추가
  - PhotoBoardDaoImpl 에 있던 SQL문 이 파일로 옮기기
- julja/gms/conf/mybatis-config.xml 변경 
  - PhotoBoardMapper 파일의 경로 등록
- julja.gms.DataLoaderListener 변경
  - SqlSessionFactory 객체 준비
  - PhotoBoardDaoImpl 에 주입
- julja.gms.servlet.PhotoBoardDetailServlet 변경
  - PhotoFileDao 주입 제거
  - PHotoBoardDao로 첨부파일까지 모두 가져온다.
- julja.gms.ServerApp 변경
  - PhotoBoardDetailServlet에 PhotoFileDao 주입 제거
  
### 7: PhotoFileDaoImpl 에 Mybatis 적용

- julja.gms.dao.mariadb.PhotoFileDaoImpl 클래스 변경
  - SQL 뜯어내어 PhotoFileMapper.xml로 옮기기
  - JDBC 코드 뜯어내고 그 자리에 Mybatis 클래스로 대체
- julja/gms/mapper/PhotoFileMapper.xml 추가
  - PhotoFileDaoImpl 에 있던 SQL문 이 파일로 옮기기
- julja/gms/conf/mybatis-config.xml 변경 
  - PhotoFileMapper 파일의 경로 등록
- julja.gms.DataLoaderListener 변경
  - SqlSessionFactory 객체 준비
  - PhotoFileDaoImpl 에 주입

### 8: 기존의 트랜잭션이 작동하지 않음 확인

- 사진 게시글 등록
- 사진 파일 등록할 때, 오류가 발생하도록 긴 파일명 입력
- 오류가 발생한 후에 사진 게시글이 등록되었는지 취소되었는지 확인
- 취소되지 않은 이유는 Mybatis의 SqlSession이 자체적으로 커넥션 관리하기 때문이다.
