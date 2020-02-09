# 32_6 - 커맨드 패턴을 적용하여 요청 처리 메서드 객체화 

## 목표

- 커맨드 패턴의 동작 원리
- 커맨드 패턴 적용

## 소스 및 결과

- src/main/java/julja/gms/ServerApp.java 변경 

### 1: 커맨드 패턴의 인터페이스 정의하라.

- julja.servlet 패키지 생성
- julja.servlet.Servlet 인터페이스 정의

### 2: 각각의 요청 처리 메서드를 인터페이스 규칙에 따라 클래스를 정의하라.
 
- listBoard()를 BoardListServlet 클래스로 정의한다.
- addBoard()를 BoardAddServlet 클래스로 정의한다.
  - detailBoard() : 게시물 조회 요청 처리
  - updateBoard() : 게시물 변경 요청 처리
  - deleteBoard() : 게시물 삭제 요청 처리
  - listUser() : 유저 목록 데이터 요청 처리
  - addUser() : 유저 데이터 등록 요청 처리
  - detailUser() : 유저 조회 요청 처리
  - updateUser() : 유저 변경 요청 처리
  - deleteUser() : 유저 삭제 요청 처리
  - listGame() : 게임 목록 데이터 요청 처리
  - addGame() : 게임 데이터 등록 요청 처리
  - detailGame() : 게임 조회 요청 처리
  - updateGame() : 게임 변경 요청 처리
  - deleteGame() : 게임 삭제 요청 처리      
