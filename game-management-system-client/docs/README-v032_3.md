# 32_3 - LMS 명령 입력 받는 기능 추가

## 목표

- 사용자로부터 명령 입력받기

## 실습 소스 및 결과

- src/main/java/julja/util 패키지 추가
- src/main/java/julja/util/Prompt.java 추가
- src/main/java/julja/gms/handler 패키지 추가
- src/main/java/julja/gms/handler/Command.java 추가
- src/main/java/julja/gms/ClientApp.java 변경

## 실습  

### 1: 31번 프로젝트의 App 클래스에서 명령 입력 부분 가져오기

- julja.util 패키지 생성
- julja.gms.util.Prompt.java 클래스 가져오기
- julja.gms.hadler 패키지 생성
- julja.gms.handler.Command 인터페이스 가져오기
- ClientApp.java 변경
  - 사용자가 입력한 명령을 처리하는 코드 가져오기