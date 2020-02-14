# 35 - 스레드풀을 이용하여 스레드 재사용

## 학습목표

- `Flyweight` 디자인 패턴
- `Flyweight` 디자인 패턴 응용 기법 Pooling 기법의 동작 원리
- Pooling 기법으로 객체 재활용
- Thread를 종료시키지 않고 재활용하기
- Thread의 wait()/notify()
- Thread Pool 적용

## 소스 및 결과

- src/main/java/julja/gms/ServerApp.java 변경


### 1: 스레드 풀을 적용하여 스레드 관리

- julja.gms.ServerApp 변경
  - 스레드 풀 준비
  - 스레드를 생성할 때 스레드 풀 사용
