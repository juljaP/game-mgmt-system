# 53_2 - Log4j 2.x 이용한 애플리케이션 로그 처리

- Log4j 2.x 설정과 이용

## 소스 및 결과

- build.gradle 변경
- src/main/resources/log4j2.xml 추가
- src/main/java/julja/gms/AppConfig.java 변경
- src/main/java/julja/gms/DatabaseConfig.java 변경
- src/main/java/julja/gms/MybatisConfig.java 변경
- src/main/java/julja/gms/ContextLoaderListener.java 변경
- src/main/java/julja/gms/ServerApp.java 변경 

### 1: Log4j 2.x 라이브러리 추가

- 라이브러리 정보 알아내기
    - `mvnrepository.com`에서 `log4j-core` 검색
- build.gradle
    - `log4j` 라이브러리 정보를 추가
    - `$ gradle eclipse`를 실행하여 이클립스 설정 파일 갱신
    - 이클립스 워크스페이스에 로딩되어 있는 클래스를 갱신

### 2: Log4j 설정 파일 추가

- src/main/resources/log4j2.xml 추가
  - 자바 classpath 루트에 log4j2 설정 파일을 둔다.
  - log4j2의 출력 범위와 출력 대상, 출력 형식 설정하는 파일

### 3: 각 클래스의 로그 출력 Log4j2로 전환

- julja.gms.ServerApp 변경
- julja.gms.ContextLoaderListener 변경
- julja.gms.AppConfig 변경
- julja.gms.DatabaseConfig 변경
- julja.gms.MybatisConfig 변경

### 4: Mybatis에 log4j2를 설정

- julja.gms.MybatisConfig 변경
  - org.apache.ibatis.logging.LogFactory.useLog4J2Logging() 호출
  - 즉 log4j 기능 활성화
