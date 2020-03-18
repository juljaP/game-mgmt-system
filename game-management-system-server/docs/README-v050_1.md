# 50_1 - Spring IoC 컨테이너 도입

- Spring Frameowork 프로젝트에 설정
- Spring IoC 컨테이너 이용한 객체 관리

## 소스 및 결과

- build.gradle 변경
- src/main/java/julja/util/ApplicationContext.java 삭제
- src/main/java/julja/gms/AppConfig.java 추가
- src/main/java/julja/gms/ContextLoaderListener.java 변경
- src/main/java/julja/gms/ServerApp.java 변경
- src/main/java/julja/util/Component.java 삭제
- src/main/java/julja/gms/servlet/XxxServlet.java 변경
- src/main/java/julja/gms/service/impl/XxxServiceImpl.java 변경


### 1: Spring IoC 컨테이너 라이브러리 가져오기

- Spring IoC 컨테이너의 라이브러리 정보 찾기
  - `mvnrepository.com` 또는 `search.maven.org`에서 `spring-context` 이름으로 라이브러리 검색
- `build.gradle` 변경
  - 빌드 설정 파일에 의존 라이브러리 정보 추가하기
  - 예) `implementation group: 'org.springframework', name: 'spring-context', version: '5.1.2.RELEASE'`
- Gradle 빌드 도구 사용하여 라이브러리 파일을 프로젝트로 가져오기
  - `$ gradle eclipse` 명령을 실행하면, 의존 라이브러리 가져오기
  - Eclipse의 CLASSPATH 정보 갱신
  - 명령어 실행한 후 이클립스에서 프로젝트 갱신

### 2: Spring IoC 컨테이너의 설정 정보 제공하는 클래스 만들기

- julja.gms.AppConfig 추가
  - Spring IoC 컨테이너가 객체 생성하기 위해 탐색할 패키지 설정
  
  
### 3: Spring IoC 컨테이너 생성

- julja.util.ApplicationContext 삭제
  - Spring IoC 컨테이너로 대체
- julja.gms.ContextLoaderListener 변경 
  - Spring 프레임워크에서 제공하는 클래스 사용하여 IoC 컨테이너 생성
  - Spring IoC 컨테이너에 들어 있는 객체 출력해보기
    - printBeans() 추가
- julja.gms.ServerApp 변경
  - Spring IoC 컨테이너의 ApplicationContext로 교체
- 서버 실행하여 Spring IoC 컨테이너에 기본적으로 들어 있는 객체 확인
  - 서블릿이나 서비스 객체가 생성되지 않았다.
  - 서블릿과 서비스 클래스에 붙인 @Component를 Spring 것으로 교체

### 4: @Component 애노테이션을 Spring 것으로 교체

- julja.util.Component 제거
- julja.servlet.* 변경
  - Spring의 @Component로 교체
- julja.service.* 변경
  - Spring의 @Component로 교체
- julja.gms.ContextLoaderListener 변경
  - Spring의 @Component로 교체
- 서버 실행하면 Spring IoC 컨테이너 생성하는 중에 오류가 발생
  - 서비스 객체 생성하는 중에 의존 객체인 DAO가 없기 때문에 DAO 준비해야 함
  
### 5: Spring IoC 컨테이너가 자동으로 생성할 수 없는 경우 설정 클래스에서 생성

- julja.gms.AppConfig 변경
  - DAO 객체 생성하는 메서드 추가  
- julja.gms.ContextLoaderListener 변경
  - IoC 컨테이너에 저장할 객체 생성 코드 제거
- 서버 실행하면 정상적으로 동작

  
