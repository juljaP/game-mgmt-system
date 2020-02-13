# 33_1 - 서버의 `Stateless` 통신 방식에 맞춰 클라이언트 변경

## 목표

- `Stateful`을 `Stateless` 통신 방식으로 바꾸기
- `Stateless` 통신 방식의 특징과 장단점 

## 소스 및 결과

###  1: `Stateful` 통신 방식을 `Stateless` 통신 방식으로 바꾸기

- com.julja.gms.ClientApp 변경
  - 한 번 연결에 요청/응답을 한 번만 수행
