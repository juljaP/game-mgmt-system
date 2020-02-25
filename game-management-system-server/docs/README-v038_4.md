# 38_4 - 트랜잭션 적용 후: 사진 게시글 입력과 첨부 파일 입력을 한 단위로 다루기

- 여러 개의 DB 변경 작업을 한 작업 단위로 묶는 트랜잭션
- `commit`과 `rollback` 활용
- 트랜잭션 사용하기 전의 문제점

## 소스 및 결과

- src/main/java/julja/util/Prompt.java 추가
- src/main/java/julja/servlet/XxxServlet.java 변경


###1: 트랜잭션을 사용하기 전의 문제점을 확인

`ClientApp` 실행 예:
```
명령> /photoboard/add
제목?
ok2
수업?
1
최소 한 개의 사진 파일을 등록해야 합니다.
파일명 입력 없이 그냥 엔터를 치면 파일 추가를 마칩니다.
사진 파일?
ok1.gif
사진 파일?
ok2.gif
사진 파일?
0123456789001234567890012345678900123456789001234567890
0123456789001234567890012345678900123456789001234567890
0123456789001234567890012345678900123456789001234567890
0123456789001234567890012345678900123456789001234567890
0123456789001234567890012345678900123456789001234567890
0123456789001234567890012345678900123456789001234567890
사진 파일?

java.sql.SQLDataException: (conn=12) Data too long for column 'PATH' at row 1 : (conn=12) Data too long for column 'PATH' at row 1

명령> /photoboard/detail
번호?
1
제목: ok2
작성일: 2018-11-14
조회수: 0
수업: 2
사진 파일:
> ok1.gif
> ok2.gif
```

### 2: 사진 게시글 입력과 첨부파일 입력을 한 단위로 다루기

- julja.gms.servlet.PhotoBoardAddServlet 변경
  - 게시글 입력과 첨부파일 입력 부분 실행 전에 수동 commit으로 설정
  - 성공하면 commit(), 실패하면 rollback() 
  
### 3: 사진 게시글 변경과 첨부파일 삭제, 입력을 한 단위로 다루기 

- julja.gms.servlet.PhotoBoardUpdateServlet 변경
  - 게시글 변경과 첨부파일 삭제,입력 부분을 실행하기 전에 수동 commit으로 설정
  - 성공하면 commit(), 실패하면 rollback() 

### 4: 사진 게시글 삭제와 첨부파일 삭제를 한 단위로 다루기 

- julja.gms.servlet.PhotoBoardDeleteServlet 변경
  - 게시글 삭제와 첨부파일 삭제를 실행하기 전에 수동 commit으로 설정
  - 성공하면 commit(), 실패하면 rollback() 
  