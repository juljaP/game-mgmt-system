# 32_1 - 자바 서버 프로젝트 만들기

## 목표

- gradle을 이용하여 자바 프로젝트 생성
- eclipse로 임포트

## 소스 및 결과

- src/main/java/julja/gms/ServerApp.java 추가

### 1: 프로젝트 폴더 생성

- 'game-mgmt-system/game-management-system-server' 디렉토리 생성

### 2: 프로젝트 폴더에 자바 프로젝트 생성

- '$ gradle init' 실행

### 3: 이클립스 IDE로 임포트

- 'build.gradle' 변경
  - 'eclipse' gradle 플러그인을 추가
  - 'javaCompile'을 설정
- '$ gradle eclipse' 실행
  - gradle을 실행하여 이클립스 설정 파일 생성
- 이클립스에서 프로젝트 폴더 임포트

### 4: 프로젝트 시작 클래스 변경

- 'ServerApp.java' 생성
  - 기존의 'App.java'의 클래스 이름을 변경
  - 소스 코드를 정리.
  - '클라이언트 수업 관리 시스템입니다' 문구 출력.
- 'src/test/java/ServerAppTest.java' 생성
  - 기존의 'AppTest.java'의 클래스 이름 변경
  - 소스 코드 정리
- ServerApp.java 실행하여 결과 확인  

