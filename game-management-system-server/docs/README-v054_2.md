# 54_2 - 출력 콘텐트에 HTML 형식 적용

- HTML 태그 사용

## 소스 및 결과

- src/main/java/julja/gms/servlet/BoardXxxServlet.java 변경
- src/main/java/julja/gms/servlet/UserXxxServlet.java 변경
- src/main/java/julja/gms/servlet/GameXxxServlet.java 변경
- src/main/java/julja/gms/servlet/PhotoBoardXxxServlet.java 변경
- src/main/java/julja/gms/ServerApp.java 변경


### 1: 게시글 목록을 출력할 때 HTML 형식으로 콘텐트를 출력

- julja.gms.servlet.BoardListServlet 변경

### 2: 웹브라우저에게 게시글 데이터 입력 요구

- julja.gms.servlet.BoardAddFormServlet 추가
  - 웹브라우저에게 게시글 데이터 입력을 요구하는 HTML을 보낸다.
- julja.gms.servlet.BoardListServlet 변경
  - /board/addForm 을 요청하는 링크를 추가


### 3: 웹브라우저가 보낸 데이터 받기

- julja.gms.ServerApp 변경
  - request-uri에서 자원의 경로와 데이터 분리
  - 예) /board/add?title=aaaa
  - 자원의 경로: /board/add
  - 데이터: title=aaaa

### 4: 웹브라우저가 보낸 게시글 데이터 저장

- julja.gms.servlet.BoardAddServlet 변경
  - 웹브라우저가 보낸 게시글을 입력
  - 웹브라우저에게 게시글 입력 결과를 보낸다.

### 5: 게시글 상세 정보를 출력

- julja.gms.servlet.BoardDetailServlet 변경
  - 웹브라우저가 보낸 번호의 게시글을 가져온다.
  - 웹브라우저에게 게시글 상세 정보를 HTML 형식으로 만들어 보낸다.
- julja.gms.servlet.BoardListServlet 변경
  - /board/detail 을 요청하는 링크 추가  
  
### 6: 게시글 삭제

- julja.gms.servlet.BoardDeleteServlet 변경
  - 웹브라우저가 보낸 번호의 게시글을 삭제
  - 웹브라우저에게 게시글 삭제 결과를 HTML 형식으로 만들어 보낸다.
- julja.gms.servlet.BoardDetailServlet 변경
  - /board/delete 을 요청하는 링크 추가
  
### 7: 게시글 변경폼 만들기

- julja.gms.servlet.BoardDetailServlet 변경
  - /board/updateForm 을 요청하는 링크를 추가
- julja.gms.servlet.BoardUpdateFormServlet 추가
  - 웹브라우저에게 게시글 데이터 변경 요구하는 HTML을 보낸다.

### 8: 게시글 변경

- julja.gms.servlet.BoardUpdateServlet 변경
  - 웹브라우저가 보낸 게시글을 변경
  - 웹브라우저에게 게시글 변경 결과 보낸다.
  
### 9: 회원 관리 서블릿 모두 변경

### 10: 수업 관리 서블릿 모두 변경

### 11: 사진게시글 관리 서블릿 모두 변경

### 12: 로그인 서블릿 모두 변경
  
  