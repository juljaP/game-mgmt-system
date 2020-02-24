# 37_5 - Application Server : 서버 종료 명령 처리

- Application Server 아키텍처의 구성과 특징
- 통신 프로토콜 규칙에 따라 동작하는 서버
- 멀티 스레드 환경에서 스레드풀의 동작 제어

## 소스 및 결과

- src/main/java/julja/gms/ServerApp.java 변경


### 1: 클라이언트의 '/server/stop' 요청을 처리

- julja.gms.ServerApp 변경
  - '/server/stop' 명령 처리
  