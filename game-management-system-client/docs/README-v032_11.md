# 32_11 - 서버에서 제공한 프록시 객체를 사용하여 데이터 처리

## 목표

- 프록시 패턴의 이점

## 소스 및 결과

- src/main/java/julja/gms/dao/BoardDao.java 추가
- src/main/java/julja/gms/dao/proxy/XxxDaoProxy.java 추가
- src/main/java/julja/gms/handler/XxxCommand.java 변경
- src/main/java/julja/gms/ClientApp.java 변경 

### 1: 서버 프로젝트(32_11)에서 DAO 프록시 클래스 가져오기

- julja.gms.dao.XxxDao 인터페이스 가져오기
- julja.gms.dao.proxy 패키지와 그 하위 클래스를 모두 가져오기

### 2: BoardXxxCommand 객체가 BoardDaoProxy 객체를 사용하여 데이터 처리

- julja.gms.handler.BoardXxxCommand 클래스 변경
  - 입출력 스트림 필드 제거
  - 생성자에서 프록시 객체 받기
  - 프록시 객체를 사용하여 데이터 처리
- julja.gms.ClientApp 변경
  - BoardDaoProxy 객체 생성
  - BoardXxxCommand 객체 주입

### 3: GameXxxCommand 객체가 GameDaoProxy 객체를 사용하여 데이터 처리

- julja.gms.handler.GameXxxCommand 클래스 변경
  - 입출력 스트림 필드 제거
  - 생성자에서 프록시 객체 받기
  - 프록시 객체를 사용하여 데이터 처리
- julja.gms.ClientApp 변경
  - GameDaoProxy 객체 생성
  - GameXxxCommand 객체에 주입
  
### 4: UserXxxCommand 객체가 UserDaoProxy 객체를 사용하여 데이터 처리

- julja.gms.handler.UserXxxCommand 클래스 변경
  - 입출력 스트림 필드 제거
  - 생성자에서 프록시 객체 받기
  - 프록시 객체를 사용하여 데이터 처리
- julja.gms.ClientApp 변경
  - UserDaoProxy 객체 생성
  - UserXxxCommand 객체에 주입
