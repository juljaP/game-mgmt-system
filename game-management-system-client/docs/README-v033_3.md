# 33_3 - 리팩토링: 서버 연결 부분 캡슐화

## 목표

- 리팩토링

## 코드 및 결과

- src/main/java/julja/gms/dao/proxy/Worker.java 추가
- src/main/java/julja/gms/dao/proxy/DaoProxyHelper.java 추가
- src/main/java/julja/gms/dao/proxy/XxxDaoProxy.java 변경
- src/main/java/julja/gms/ClientApp.java 변경
  

### 1: 서버와 통신을 담당할 실제 작업의 규칙 정의

- julja.gms.dao.proxy.Worker 인터페이스 정의

### 2: DaoProxy를 도와 서버와의 연결을 담당할 객체 정의 

- julja.gms.dao.proxy.DaoProxyHelper 정의

### 3: DaoProxyHelper를 사용하도록 DaoProxy 변경

- julja.gms.dao.proxy.XxxDaoProxy 변경

###4: DaoProxyHelper가 추가된 것에 맞춰 ClientApp 변경

- julja.gms.ClientApp 변경
  