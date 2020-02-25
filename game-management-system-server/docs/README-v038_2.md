# 38_2 - 트랜잭션 적용 전: 사진 게시글에 첨부파일 추가

- 여러 개의 DB 변경 작업을 한 작업 단위로 묶는 트랜잭션
- `commit`과 `rollback` 활용

## 소스 및 결과

- src/main/java/julja/domain/PhotoFile.java 추가 
- src/main/java/julja/dao/PhotoFileDao.java 추가
- src/main/java/julja/dao/mariadb/PhotoFileDaoImpl.java 추가
- src/main/java/julja/dao/mariadb/PhotoBoardDaoImpl.java 변경
- src/main/java/julja/servlet/PhotoBoardDetailServlet.java 변경
- src/main/java/julja/servlet/PhotoBoardAddServlet.java 변경
- src/main/java/julja/servlet/PhotoBoardUpdateServlet.java 변경
- src/main/java/julja/servlet/PhotoBoardDeleteServlet.java 변경
- src/main/java/julja/gms/DataLoaderListener.java 변경
- src/main/java/julja/gms/ServerApp.java 변경


### 1: `게임 사진 게시판`에 사진 파일 첨부 기능 추가

- julja.gms.domain.PhotoFile 추가
  - 사진 파일의 타입 정의
- julja.gms.dao.PhotoFileDao 인터페이스 추가
  - 사진 파일의 CRUD 관련 메서드 호출 규칙 정의
- julja.gms.dao.mariadb.PhotoFileDaoImpl 추가
  - PhotoFileDao 인터페이스 구현
- julja.gms.DataLoaderListener 변경
  - PhotoFileDao 객체 생성

### 2: '/photoboard/add' 명령 처리

사진 게시글 입력할 때 사진 파일 첨부할 수 있게 변경

- julja.gms.dao.mariadb.PhotoBoardDaoImpl 변경
  - insert() 메서드 변경
  - insert 한 후에 자동 증가 PK 값을 리턴 받기
- julja.gms.servlet.PhotoBoardAddServlet 변경
  - GameDao 객체 주입 받아 게임 번호의 유효성 검사
  - 사진 게시글 입력 받을 때 첨부 파일도 입력 받기
- julja.gms.ServerApp 변경
  - `PhotoBoardAddServlet` 객체에 GameDao와 PhotoFileDao 객체 주입 

`ClientApp` 실행 예:
```
명령> /photoboard/add
제목?
ok
게임 번호?
1
최소 한 개의 사진 파일을 등록해야 합니다.
파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.
사진 파일?
   <== 입력 없이 엔터 치면?
최소 한 개의 사진 파일을 등록해야 합니다.
사진 파일?
a1.gif
사진 파일?
a2.gif
사진 파일?
a3.gif
사진 파일?

사진을 저장했습니다.
```
    
### 3: '/photoboard/detail' 명령 처리

사진 게시글을 출력할 때 첨부 파일 목록도 함께 출력

- julja.gms.dao.PhotoFileDao 인터페이스 변경
  - 사진 파일 목록을 리턴하는 메서드 추가
  - findAll(int boardNo)
- julja.gms.dao.mariadb.PhotoFileDaoImpl 변경
  - PhotoFileDao 인터페이스에 추가된 메서드 구현
- julja.gms.servlet.PhotoBoardDetailServlet 변경
  - PhotoFileDao 의존 객체 주입받기
  - 사진 게시글 다음에 첨부파일 목록 출력
- julja.gms.ServerApp 변경
  - `PhotoBoardDetailServlet` 객체에 PhotoFileDao 객체 주입 
  
`ClientApp` 실행 예:
```
명령> /photoboard/detail
번호?
7
제목: Zelda
작성일: 2018-11-14
조회수: 0
게임: 2
사진 파일:
> ppt1.jpeg
> pp2.jpeg
> pp3.jpeg
```

### 4: PhotoFile 객체의 생성자 및 셋터의 활용

- 인스턴스의 초기 값 설정할 수 있는 생성자 추가

생성자 통해 인스턴스의 초기 값 설정 I:
- julja.gms.domain.PhotoFile 변경
  - PhotoFile(filepath, boardNO) 생성자 추가
- julja.gms.servlet.PhotoBoardAddServlet 변경
  - PhotoFile(filepath, boardNo) 생성자 사용

생성자 통해 인스턴스의 초기 값 설정 II:
- julja.gms.domain.PhotoFile 변경
  - PhotoFile(int no, filepath, boardNO) 생성자 추가
- julja.gms.dao.mariadb.PhotoFileDaoImpl 변경
  - PhotoFile(no, filepath, boardNo) 생성자 사용

셋터 메서드 통해 인스턴스의 초기 값 설정:
- julja.gms.domain.PhotoFile 변경
  - 셋터 메서드가 인스턴스 주소 리턴하게 변경
- julja.gms.servlet.PhotoBoardAddServlet 변경
  - PhotoFile 객체 만들 때 셋터 메서드로 값 설정
- julja.gms.dao.mariadb.PhotoFileDaoImpl 변경
  - PhotoFile 객체 만들 때 셋터 메서드로 값 설정
  

### 5: '/photoboard/update' 명령 처리

사진 게시글 변경할 때 첨부 파일도 변경

- julja.gms.dao.PhotoFileDao 인터페이스 변경
  - 사진 파일 삭제하는 메서드 추가
  - deleteAll(int boardNo)
- julja.gms.dao.mariadb.PhotoFileDaoImpl 변경
  - PhotoFileDao 인터페이스에 추가된 메서드 구현
- julja.gms.servlet.PhotoBoardUpdateServlet 변경
  - 사진 게시글의 첨부파일 변경
- julja.gms.ServerApp 변경
  - `PhotoBoardUpdateServlet` 객체에 PhotoFileDao 객체 주입 

`ClientApp` 실행 예:
```
명령> /photoboard/update
번호?
7
제목(Zelda)?
Dream
사진 파일:
> aaa1.jpeg
> aaa2.jpeg

사진은 일부만 변경할 수 없습니다.
전체를 새로 등록해야 합니다.
사진을 변경하시겠습니까?(y/N)
y
최소 한 개의 사진 파일을 등록해야 합니다.
파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.
사진 파일?

최소 한 개의 사진 파일을 등록해야 합니다.
사진 파일?
ppt1.jpeg
사진 파일?
pp2.jpeg
사진 파일?
pp3.jpeg
사진 파일?

사진을 변경했습니다.
```

### 6: '/photoboard/delete' 명령 처리

사진 게시글 삭제할 때 첨부 파일도 삭제

- julja.gms.servlet.PhotoBoardDeleteServlet 변경
  - 첨부 파일 삭제 할 때 사용할 PhotoFileDao 객체 주입 받기
  - 사진 게시글 삭제 전에 첨부 파일 먼저 삭제
  - 사진 게시글 삭제 
- julja.gms.ServerApp 변경
  - `PhotoBoardDeleteServlet` 객체에 PhotoFileDao 객체 주입 
  
`ClientApp` 실행 예:
```
명령> /photoboard/delete
번호?
99
해당 사진 게시글을 찾을 수 없습니다.

명령> /photoboard/delete
번호?
7
사진 게시글을 삭제했습니다.
```
  
