# 51_2 - Spring IoC 설정 파일(Java Config)을 분리

- Spring Java Config 파일 다루기

## 소스 및 결과

- src/main/java/julja/gms/AppConfig.java 변경
- src/main/java/julja/gms/DatabaseConfig.java 추가
- src/main/java/julja/gms/MybatisConfig.java 추가
- src/main/java/julja/gms/ContextLoaderListener.java 변경


### 1: 데이터베이스 관련 설정을 분리

- julja.gms.DatabaseConfig 추가
  - AppConfig에서 데이터베이스 관련 객체 생성 코드 가져오기
- julja.gms.AppConfig 변경
  
### 2: Mybatis 관련 설정을 분리

- julja.gms.MybatisConfig 추가
  - AppConfig에서 Mybatis 관련 객체 생성 코드를 가져온다.
- julja.gms.AppConfig 변경

### 3: Spring IoC 컨테이너를 생성할 때 Java Config 모두 지정

- julja.gms.ContextLoaderListener 변경
  - Spring IoC 컨테이너 생성 코드를 변경
  
### 4: @Configuration 애노테이션을 사용하여 Java Config 설정

- julja.gms.DatabaseConfig 변경
  - @Configuration 애노테이션을 붙인다.
- julja.gms.MybatisConfig 변경
  - @Configuration 애노테이션을 붙인다.
- julja.gms.ContextLoaderListener 변경
  - Spring IoC 컨테이너를 생성할 때 Java Config로 AppConfig 만 지정