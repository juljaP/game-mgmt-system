# 53_1 - Log4j 1.2.x 이용한 애플리케이션 로그 처리

## `Log4j` 라이브러리
- 개발 중에는 로그 자세하게 출력하고 개발이 완료된 후에는 중요 로그만 출력하도록 조정하는 기능 제공
- 로그의 출력 형식 지정할 수 있다. 
- 출력 대상도 콘솔, 파일, 네트워크, DB 등 다양하게 지정할 수 있다.

## 소스 및 결과

- src/main/java/julja/gms/DatabaseConfig.java 변경
- src/main/java/julja/gms/AppConfig.java 변경 
- src/main/java/julja/gms/ContextLoaderListener.java 변경 
- src/main/java/julja/gms/MybatisConfig.java 변경 
- src/main/java/julja/gms/ServiceApp.java 변경 

### 1: Log4j 1.2.x 라이브러리 추가

- 라이브러리 정보 알아내기
    - `mvnrepository.com`에서 `log4j` 검색
- build.gradle
    - `log4j` 라이브러리 정보 추가
    - `$ gradle eclipse` 실행하여 이클립스 설정 파일 갱신
    - 이클립스 워크스페이스에 로딩되어 있는 클래스 갱신


### 2: Log4j 설정 파일 추가

- src/main/resources/log4j.properties 추가
  - 자바 classpath 루트에 log4j 설정 파일 두기
  - log4j의 출력 범위와 출력 대상, 출력 형식 설정하는 파일


### 3: 각 클래스의 로그 출력 Log4j로 전환

- julja.lms.ServerApp 변경
- julja.lms.ContextLoaderListener 변경
- julja.lms.AppConfig 변경
- julja.lms.DatabaseConfig 변경
- julja.lms.MybatisConfig 변경

### 4: Mybatis에 log4j 설정

- julja.gms.MybatisConfig 변경
  - org.apache.ibatis.logging.LogFactory.useLog4JLogging() 호출
  - 즉 log4j 기능을 활성화시킨다.

