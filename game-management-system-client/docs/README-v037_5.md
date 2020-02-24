# 37_5 - Application Server : 서버 종료 명령 처리


- Application Server 아키텍처의 구성과 특징
- 통신 프로토콜 규칙에 따라 동작하는 클라이언트
- 멀티 스레드 환경에서 스레드풀의 동작 제어

## 소스 및 결과

- src/main/java/julja/gms/ClientApp.java 변경

### 1: '/server/stop' 명령을 처리

- julja.gms.ClientApp 변경
  - 사용자가 '/server/stop'을 입력했을 때 서버가 종료 작업을 즉시 할 수 있도록 두 번 요청

