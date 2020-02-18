# 36_1 - DBMS 통한 데이터 관리

- 오픈 소스 DBMS `MariaDB`

## 소스 및 결과

- src/main/java/julja/gms/dao/mariadb 패키지 생성
- src/main/java/julja/gms/dao/mariadb/BoardDaoImpl.java 생성
- src/main/java/julja/gms/dao/mariadb/GameDaoImpl.java 생성
- src/main/java/julja/gms/dao/mariadb/UserDaoImpl.java 생성
- src/main/java/julja/gms/handler/BoardAddCommand.java 변경
- src/main/java/julja/gms/handler/BoardUpdateCommand.java 변경
- src/main/java/julja/gms/handler/BoardDeleteCommand.java 변경
- src/main/java/julja/gms/handler/GameAddCommand.java 변경
- src/main/java/julja/gms/handler/GameUpdateCommand.java 변경
- src/main/java/julja/gms/handler/GameDeleteCommand.java 변경
- src/main/java/julja/gms/handler/UserAddCommand.java 변경
- src/main/java/julja/gms/handler/UserUpdateCommand.java 변경
- src/main/java/julja/gms/handler/UserDeleteCommand.java 변경
- src/main/java/julja/gms/ClientApp.java 변경


### 1: 애플리케이션에서 사용할 사용자와 데이터베이스를 MariaDB에 추가

- MariaDB 에 연결하기 

$ mysql -u root -p
Enter password: 암호입력

- 사용자 생성하기

CREATE USER 'study'@'localhost' IDENTIFIED BY '1111';

- 데이터베이스 생성하기

CREATE DATABASE studydb
  CHARACTER SET utf8
  COLLATE utf8_general_ci;


- 사용자에게 DB 사용 권한을 부여하기

GRANT ALL ON studydb.* TO 'study'@'localhost';

- MariaDB에 `study` 사용자 아이디로 다시 접속하기

$ mysql -u study -p
Enter password: 1111

- `study` 아이디로 사용할 수 있는 데이터베이스 목록 보기

> show databases;

### 2: 애플리케이션에서 사용할 테이블과 예제 데이터 준비.

- `study` 아이디로 MariaDB에 접속한 후 기본으로 사용할 데이터베이스를 `studydb`로 설정하기
> use studydb;

- 애플리케이션에서 사용할 테이블 생성

./sql.txt

### 3: 프로젝트에 `MariaDB` JDBC 드라이버를 추가

- build.gradle
    - 의존 라이브러리에 MariaDB JDBC Driver 정보 추가
    - 예를 들면, `mvnrepository.com`에서 키워드 `mariadb jdbc`로 검색하면 **MariaDB Java Client** 라이브러리를 찾을 수 있다.
    - 최신 버전의 라이브러리 정보를 가져오면 된다.

build.gradle 

plugins {
    id 'java'
    id 'application'
    id 'eclipse'
}

tasks.withType(JavaCompile) {
    options.encoding = 'UTF-8'
    sourceCompatibility = '1.8'
    targetCompatibility = '1.8'
}

mainClassName = 'App'

dependencies {
    implementation group: 'org.mariadb.jdbc', name: 'mariadb-java-client', version: '2.5.4'
    compile 'com.google.guava:guava:23.0'
    testCompile 'junit:junit:4.12'
}

repositories {
    jcenter()
}
```

`gradle`을 실행하여 이클립스 설정 파일을 갱신하기

$ gradle eclipse


이클립스 워크스페이스의 프로젝트 갱신하기
> 이클립스에서도 프로젝트에 `Refresh`를 수행해야 라이브러리가 적용된다.

### 4: MariaDB에서 제공하는 Proxy를 사용하여 DBMS와 연동하여 데이터 처리 작업을 수행할 DAO를 정의

- julja.gms.dao.mariadb 패키지 생성
- julja.gms.dao.mariadb.BoardDaoImpl 추가
- julja.gms.dao.mariadb.GameDaoImpl 추가
- julja.gms.dao.mariadb.UserDaoImpl 추가

### 5: Command 객체의 기존 DAO를 MariaDB 연동 DAO로 교체

- julja.gms.ClientApp 변경

### 6: DBMS 연동에 맞춰서 Command 객체를 변경

- julja.gms.handler.XxxAddCommand 변경
- julja.gms.handler.XxxUpdateCommand 변경
- julja.gms.handler.XxxDeleteCommand 변경
