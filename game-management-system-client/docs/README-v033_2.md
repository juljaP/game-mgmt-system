# 33_2 - 리팩토링: 요청할 때 마다 프록시와 커맨드를 생성하는 부분 개선

## 목표

- 리팩토링

## 소스 및 결과

- src/main/java/julja/gms/dao/proxy/XxxDaoProxy.java 변경
- src/main/java/julja/gms/ClientApp.java 변경


### 1: 프록시 클래스 생성 부분을 변경

- julja.gms.dao.proxy.XxxDaoProxy 변경
  - 요청할 때 서버에 연결
