# 37_1 - Application Server


- Application Server 아키텍처의 구성과 특징
- 통신 프로토콜 규칙에 따라 동작하는 서버를

## 소스 및 결과

- src/main/java/julja/gms/ServerApp.java 변경


### 1: 클라이언트와 서버 사이의 기본 통신 규칙

- 요청과 응답은 `Stateless` 방식으로 처리
- 클라이언트가 요청할 때 서버와 연결하고, 서버에서 응답하면 연결을 끊는다.

규칙1) 단순한 명령어 전송과 실행 결과 수신
```
[클라이언트]                                        [서버]
서버에 연결 요청        -------------->           연결 승인
명령(CRLF)              -------------->           명령처리
화면 출력               <--------------           응답(CRLF)
화면 출력               <--------------           응답(CRLF)
명령어 실행 완료        <--------------           !end!(CRLF)
서버와 연결 끊기
```

### 2: '통신 규칙1'에 따라 클라이언트의 요청에 응답

- julja.gms.ServerApp 변경
  - 클라이언트 요청에 대해 간단한 인사말을 출력하도록 변경
