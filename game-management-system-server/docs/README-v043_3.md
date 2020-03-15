# 43_3 - MyBatis의 dynamic sql 문법 사용

- Mybatis에서 동적 SQL 생성

## 소스 및 결과

- src/main/java/julja/util/Prompt.java 변경
- src/main/java/julja/gms/ServerApp.java 변경
- src/main/java/julja/gms/mapper/XxxDaoImpl.java 변경
- src/main/java/julja/gms/dao/PhotoFileDao.java 변경
- src/main/java/julja/gms/dao/GameDao.java 변경
- src/main/java/julja/gms/dao/mariadb/GameDaoImpl.java 변경
- src/main/java/julja/gms/dao/mariadb/UserDaoImpl.java 변경
- src/main/java/julja/gms/dao/mariadb/PhotoBoardDaoImpl.java 변경
- src/main/java/julja/gms/dao/mariadb/PhotoFileDaoImpl.java 변경
- src/main/java/julja/gms/servlet/PhotoBoardAddServlet.java 변경
- src/main/java/julja/gms/servlet/PhotoBoardUpdateServlet.java 변경
- src/main/java/julja/gms/servlet/UserUpdateServlet.java 변경
- src/main/java/julja/gms/servlet/GameUpdateServlet.java 변경
- src/main/java/julja/gms/servlet/GameSearchServlet.java 변경

### 1: `sql` 태그를 사용하여 공통 SQL 코드를 분리

- src/main/resources/julja/gms/mapper/*Mapper.xml
  
### 2: `foreach` 태그를 사용하여 insert 문 생성하기

사진 게시글의 첨부파일을 입력할 때, 여러 값들을 한 번에 입력

- julja.gms.servlet.PhotoBoardAddServlet 변경
  - 파일 목록을 한 번에 insert 하기
- julja.gms.servlet.PhotoBoardUpdateServlet 변경
  - 파일 목록을 한 번에 insert 하기  
- julja.gms.dao.PhotoFileDao 변경
  - insert(PhotoFile) 메서드를 insert(PhotoBoard) 로 변경
- julja.gms.dao.mariadb.PhotoFileDaoImpl 변경
  - insert()를 변경
- src/main/resources/julja/gms/mapper/PhotoFileMapper.xml 변경
  - insertPhotoFile SQL 변경
  - `foreach` 태그를 적용하여 여러 개의 값을 한 번에 insert 
  
### 3: `set` 태그를 사용하여 update할 때 일부 컬럼만 변경

데이터를 변경할 때 일부 컬럼의 값만 변경하기

- src/main/resources/julja/gms/mapper/GameMapper.xml 변경
  - updateGame SQL 변경
- julja.util.Prompt 변경
  - 클라이언트가 보낸 값을 지정된 타입으로 변경할 수 없을 때의 예외 처리
- julja.gms.servlet.GameUpdateServlet 변경
  - 클라이언트가 값을 보내지 않은 항목은 빈문자열이나 null, 0으로 설정
  - 이 경우 update 대상 컬럼에서 제외된다.
- src/main/resources/julja/gms/mapper/UserMapper.xml 변경
  - updateUser SQL을 변경
- julja.gms.servlet.UserUpdateServlet 변경
  - 클라이언트가 값을 보내지 않은 항목은 빈문자열이나 null, 0으로 설정
  - 이 경우 update 대상 컬럼에서 제외된다.
  
### 4: `where` 태그를 사용하여 검색 조건을 변경 

수업을 검색(수업명, 시작일, 종료일, 총강의시간, 일강의시간)하는 기능을 추가
검색 조건은 AND 연산으로 처리

- src/main/resources/julja/gms/mapper/GameMapper.xml 변경
  - selectGame SQL문을 변경
  - `where` 태그를 적용하여 조건을 만족하는 데이터를 찾는다. 
- julja.gms.dao.GameDao 변경
  - findByKeyword() 메서드를 추가
- julja.gms.servlet.GameSearchServlet 추가 
  - 검색 요청을 처리
- julja.gms.ServerApp 변경
  - GameSearchServlet 객체 등록 
  