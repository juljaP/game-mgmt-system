# 38_1 - 트랜잭션 적용 전: 사진 게시판 추가

- 여러 개의 DB 변경 작업 한 작업 단위로 묶는 트랜잭션 다룰 수 있다.
- `commit`과 `rollback` 활용할 수 있다.

## 소스 및 결과

- src/main/java/julja/domain/PhotoBoard.java 추가
- src/main/java/julja/dao/PhotoBoardDao.java 추가
- src/main/java/julja/dao/mariadb/PhotoBoardDaoImpl.java 추가
- src/main/java/julja/servlet/PhotoBoardListServlet.java 추가
- src/main/java/julja/servlet/PhotoBoardDetailServlet.java 추가
- src/main/java/julja/servlet/PhotoBoardAddServlet.java 추가
- src/main/java/julja/servlet/PhotoBoardUpdateServlet.java 추가
- src/main/java/julja/servlet/PhotoBoardDeleteServlet.java 추가
- src/main/java/julja/gms/DataLoaderListener.java 변경
- src/main/java/julja/gms/ServerApp.java 변경


### 1: `게임 사진 게시판` 데이터 다룰 DAO 생성

- julja.gms.domain.PhotoBoard 추가
  - 사진 게시물의 데이터 타입 정의
- julja.gms.dao.PhotoBoardDao 인터페이스 추가
  - 사진 게시물의 CRUD 관련 메서드 호출 규칙 정의
- julja.gms.dao.mariadb.PhotoBoardDaoImpl 추가
  - PhotoBoardDao 인터페이스 구현
- julja.gms.DataLoaderListener 변경
  - PhotoBoardDao 객체 생성

### 1: '/photoboard/list' 명령 처리

- julja.gms.servlet.PhotoBoardListServlet 추가
    - 사진 게시물의 목록 출력
- julja.gms.ServerApp 변경
    - `PhotoBoardListServlet` 객체 생성하여 커맨드 맵에 보관

`ClientApp` 실행 예:
```
명령> /photoboard/list
게임번호? 1
게임명: xxxxx
----------------------------------------------------
  1, Zelda           , 2018-11-14, 0
  2, Creed            , 2018-11-14, 0
  3, null                , 2018-11-14, 0
  4, Fire Emblem              , 2018-11-14, 0
```
    
### 2: '/photoboard/detail' 명령 처리

- julja.gms.domain.PhotoBoard 변경
  - 게임 정보 담을 Game 타입의 인스턴스 필드 추가
- julja.gms.dao.mariadb.PhotoBoardDaoImp 변경
  - findByNo(사진게시글번호) 메서드 변경
  - 사진 게시글의 상세정보 가져올 때 lms_photo와 lms_Game 조인
  - lms_photo 데이터는 PhotoBoard에 저장하고, lms_Game 데이터는 Game에 저장 
- julja.gms.servlet.PhotoBoardDetailServlet 추가
    - 사진 게시물의 상세정보 출력
- julja.gms.ServerApp 변경
    - `PhotoBoardDetailServlet` 객체 생성하여 커맨드 맵에 보관

`ClientApp` 실행 예:
```
명령> /photoboard/detail
번호?
6
제목: test1
작성일: 2018-11-14
조회수: 0
게임명: xxxx

명령> /photoboard/detail
번호?
5
해당 번호의 사진 게시글이 없습니다.
```

### 3: '/photoboard/add' 명령 처리

- julja.gms.servlet.PhotoBoardAddServlet 추가
    - 사진 게시물 입력
- julja.gms.ServerApp 변경
    - `PhotoBoardAddServlet` 객체 생성하여 커맨드 맵에 보관

`ClientApp` 실행 예:
```
명령> /photoboard/add
제목?
test1
게임 번호?
2
사진을 저장했습니다.

명령> /photoboard/add
제목?
test1
게임 번호?
200
게임 번호가 유효하지 않습니다.
```

### 4: '/photoboard/update' 명령 처리

- julja.gms.servlet.PhotoBoardUpdateServlet 추가
    - 사진 게시물 변경 
- julja.gms.ServerApp 변경
    - `PhotoBoardUpdateServlet` 객체 생성하여 커맨드 맵에 보관

`ClientApp` 실행 예:
```
명령> /photoboard/update
번호?
6
제목(test1)?
test1...xx
사진을 변경했습니다.

명령> /photoboard/update
번호?
600
해당 번호의 사진 게시글이 없습니다.
```

### 5: '/photoboard/delete' 명령 처리

- julja.gms.servlet.PhotoBoardDeleteServlet 추가
    - 사진 게시물 삭제 
- julja.gms.ServerApp 변경
    - `PhotoBoardDeleteServlet` 객체 생성하여 커맨드 맵에 보관

`ClientApp` 실행 예:
```
명령> /photoboard/delete
번호?
6
사진 게시글을 삭제했습니다.

명령> /photoboard/delete
번호?
600
해당 번호의 사진 게시글이 없습니다.
```

