# 55_1 - JavaEE Servlet

- JavaEE Implements(WAS; Web Application Server)
- Tomcat 서블릿 컨테이너
- 웹 애플리케이션

## 소스 및 결과

- build.gradle 변경
- src/main/java/julja/lms/ServerApp.java 변경
- src/main/java/julja/lms/servlet/XxxServlet.java 변경

### 1: 서블릿 컨테이너 설치 및 설정

- tomcat.apache.org 사이트에서 서블릿 컨테이너 다운로드
- 특정 폴더에 압축을 풀고, 설정
  - 관리자 ID/PWD를 등록
    - $톰캣홈/conf/tomcat-users.xml
  - 관리자 로그인 활성화
    - $톰캣홈/conf/Catalina/localhost/manager.xml
- 톰캣 서버를 실행하고 웹 브라우저 통해 접속 확인

### 2: JavaEE Servlet 기술을 사용하기 위한 라이브러리를 프로젝트에 적용

- build.gradle 변경
  - search.maven.org 에서 'servlet-api'를 검색
  - 의존 라이브러리 블록에 추가
  - 의존 라이브러리 옵션은 compileOnly로 설정
  - 왜? 'servlet-api'는 컴파일할 때만 사용할 것이기 때문이다. 
- 'gradle eclipse' 실행
  - 이클립스 설정 파일을 갱신
- 이클립스 IDE의 프로젝트를 refresh 

### 3: JavaEE의 Servlet 기술을 사용하여 Spring IoC 컨테이너를 준비

- julja.lms.ContextLoaderListener 변경
  - Servlet 기술에게 제공하는 ServletContextListener를 구현
  - Spring IoC 컨테이너를 준비 
- julja.lms.context 패키지 및 하위 클래스 삭제

### 4: 기존의 서블릿 클래스를 JavaEE의 Servlet 기술을 적용하여 변경

- julja.lms.servlet.XxxServlet 변경
- julja.lms.ServerApp 삭제

### 5: 웹애플리케이션을 빌드 

- build.gradle 변경
  - 웹애플리케이션 배치 파일을 생성하기 위해 'war' 플러그인 추가
- 'gradle build'를 실행
  - 'build/libs/bitcamp-project-server.war' 파일 생성

### 6: 톰캣 서버에 배치

- $톰캣홈/webapps/ 폴더에 war 파일을 놓는다.
- 톰캣 서버를 다시 시작
  - 톰캣 서버는 bitcamp-project-server.war 파일과 
    동일한 이름으로 폴더를 만들고 압축 풀기
  - 예) $톰캣홈/webapp/bitcamp-project-server

### 7: 웹애플리케이션을 실행

- /board/list 실행
  - http://localhost:포트번호/웹애플리케이션이름/board/list
  - 웹애플리케이션 이름은 webapps/ 폴더에 압축을 푼 디렉토리 이름
  - 예) http://localhost:9999/game-management-system-server/board/list
- julja.lms.servlet.XxxServlet 변경
  - 상대 경로 지정

