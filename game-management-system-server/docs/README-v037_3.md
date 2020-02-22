# 37_3 - Application Server 구조로 변경: 통신 규칙2 추가 및 Servlet, DAO

- Application Server 아키텍처의 구성과 특징
- 통신 프로토콜 규칙에 따라 동작하는 서버

## 소스 및 결과

- src/main/java/julja/gms/dao/mariadb 디렉토리 생성
- src/main/java/julja/gms/dao/mariadb/BoardDaoImpl.java 추가
- src/main/java/julja/gms/dao/mariadb/GameDaoImpl.java 추가
- src/main/java/julja/gms/dao/mariadb/UserDaoImpl.java 추가
- src/main/java/julja/gms/DataLoaderListener.java 변경
- src/main/java/julja/gms/servlet/BoardXxxServlet.java 변경
- src/main/java/julja/gms/servlet/GameXxxServlet.java 변경
- src/main/java/julja/gms/servlet/UserXxxServlet.java 변경
- src/main/java/julja/gms/ServerApp.java 변경

## 실습  

### 1: 서버가 클라이언트에게 추가 데이터 입력을 요구할 수 있도록 통신 규칙을 변경 
규칙2) 사용자 입력을 포함하는 경우
```
[클라이언트]                                        [서버]
서버에 연결 요청        -------------->           연결 승인
명령(CRLF)              -------------->           명령처리
화면 출력               <--------------           응답(CRLF)
사용자 입력 요구        <--------------           !{}!(CRLF)
입력값(CRLF)            -------------->           입력 값 처리
화면 출력               <--------------           응답(CRLF)
사용자 입력 요구        <--------------           !{}!(CRLF)
입력값(CRLF)            -------------->           입력 값 처리
명령어 실행 완료        <--------------           !end!(CRLF)
서버와 연결 끊기
```

### 2: '통신 규칙2'에 따라 게시글 번호를 입력 받을 수 있도록 BoardDetailServlet 변경 

- julja.gms.servlet.BoardDetailServlet 변경
  - Servlet 인터페이스에 추가한 service(Scanner in, PrintStream out)을 구현
  - '통신 규칙2'에 따라 클라이언트에게 상세 조회할 게시글의 번호 요구
  - '통신 규칙1'에 따라 응답
- julja.gms.ServerApp 변경
  - '/board/detail' 명령을 처리할 서블릿을 맵에 추가

### 3: '통신 규칙2'에 따라 게시글을 입력 받을 수 있도록 BoardAddServlet을 변경 

- julja.gms.servlet.BoardAddServlet 변경
  - Servlet 인터페이스에 추가한 service(Scanner in, PrintStream out)을 구현
  - '통신 규칙2'에 따라 클라이언트에게 게시글 요구
  - '통신 규칙1'에 따라 응답
- julja.gms.ServerApp 변경
  - '/board/add' 명령을 처리할 서블릿을 맵에 추가

### 4: '통신 규칙2'에 따라 게시글을 변경할 수 있도록 BoardUpdateServlet을 변경 

- julja.gms.servlet.BoardUpdateServlet 변경
  - Servlet 인터페이스에 추가한 service(Scanner in, PrintStream out)을 구현
  - '통신 규칙2'에 따라 클라이언트에게 게시글 변경 요구
  - '통신 규칙1'에 따라 응답
- julja.gms.ServerApp 변경
  - '/board/update' 명령을 처리할 서블릿을 맵에 추가
  
  
### 5: '통신 규칙2'에 따라 게시글을 삭제할 수 있도록 BoardDeleteServlet을 변경 

- julja.gms.servlet.BoardDeleteServlet 변경
  - Servlet 인터페이스에 추가한 service(Scanner in, PrintStream out) 구현
  - '통신 규칙2'에 따라 클라이언트에게 게시글 번호를 요구
  - '통신 규칙1'에 따라 응답
- julja.gms.ServerApp 변경
  - '/board/delete' 명령을 처리할 서블릿 맵에 추가
  
### 6: '통신 규칙2'에 따라 동작하도록 나머지 Servlet 클래스도 변경 

- julja.gms.servlet.UserXxxServlet 변경
  - Servlet 인터페이스에 추가한 service(Scanner in, PrintStream out)을 구현
  - '통신 규칙2'에 따라 변경
  - '통신 규칙1'에 따라 응답
- julja.gms.servlet.GameXxxServlet 변경
  - Servlet 인터페이스에 추가한 service(Scanner in, PrintStream out)을 구현
  - '통신 규칙2'에 따라 변경
  - '통신 규칙1'에 따라 응답
- julja.gms.ServerApp 변경
  - '/User/*' 명령을 처리할 서블릿 맵에 추가
  - '/Game/*' 명령을 처리할 서블릿 맵에 추가