# 54_3 - Apache HttpComponents 라이브러리 이용한 웹서버 구현

- Apache HttpComponents 라이브러리 사용

## 소스 및 결과

- build.gradle 변경
- src/main/java/julja/gms/ServerApp.java 변경
- src/main/java/julja/gms/servlet/XxxServlet.java 변경

### 1: Apache HttpComponents 라이브러리 프로젝트에 적용

- search.maven.org 에서 라이브러리 정보 알아내기
  - 'httpclient5' 로 검색
- build.gradle 변경
  - 의존 라이브러리 추가
- `gradle eclipse` 실행
  - 이클립스 설정 파일을 갱신
- 이클립스에서 프로젝트 refresh 

### 2: HTTP 요청을 받을 때 HttpComponents 라이브러리에 있는 클래스 사용하여 처리

- julja.gms.ServerApp 변경

### 3: 서블릿의 request handler의 파라미터 변경

- julja.gms.servlet.XxxSevlet 변경
  - request handler 파라미터 PrintStream 에서 PrintWriter로 바꾸기
 