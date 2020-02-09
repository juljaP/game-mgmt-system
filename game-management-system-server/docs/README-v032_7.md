# 32_7 - 데이터 처리 코드를 별도의 클래스로 정의하여 객체화

## 목표

- DAO(Data Access Object)의 역할과 이점
- 데이터 처리 코드를 DAO로 분리

### DAO(Data Access Object)

- 데이터 처리 역할을 수행하는 객체
- 데이터 처리 방식을 캡슐화(=추상화=클래스로 정의)하여 객체의 사용을 일관성 있게 만든다.
  - 즉 데이터 처리 방식(배열, 스택, 큐, 맵, 파일, 데이터베이스 등)을 
    클래스로 포장(캡슐화)하면 데이터 처리 방식에 상관없이 메서드 사용을 통일할 수 있다.
 

## 실습 소스 및 결과

- src/main/java/julja/gms/dao 패키지 생성
- src/main/java/julja/gms/dao/BoardObjectFileDao.java 추가
- src/main/java/julja/gms/dao/GameObjectFileDao.java 추가
- src/main/java/julja/gms/dao/UserObjectFileDao.java 추가
- src/main/java/julja/gms/ServerApp.java 변경

### 1: 게시물 데이터를 처리하는 DAO 클래스 정의

- julja.gms.dao 패키지 생성
- julja.gms.BoardObjectFileDao 클래스 정의

### 2: BoardObjectFileDao 객체 적용

- julja.gms.DataLoaderListener 변경
  - 게시물 데이터를 로딩하고 저장하는 기존 코드 제거
  - 대신에 BoardObjectFileDao 객체 생성
- julja.gms.ServerApp 변경
  - Map에서 BoardObjectFileDao를 꺼내 관련 커맨드 객체에 주입
- BoardXxxServlet 변경
  - 생성자에서 List 객체를 받는 대신에 BoardObjectFileDao 객체를 받는다.
  - 데이터를 저장하고, 조회하고, 변경하고, 삭제할 때 BoardObjectFileDao 객체를 통해 처리
  
  
### 3: 게임 데이터를 처리하는 DAO 클래스를 정의하고 적용

- julja.gms.GameObjectFileDao 클래스 정의
- julja.gms.DataLoaderListener 변경
  - 게임 데이터를 로딩하고 저장하는 기존 코드 제거
  - 대신에 GameObjectFileDao 객체 생성
- julja.gms.ServerApp  변경
  - Map에서 GameObjectFileDao를 꺼내 관련 커맨드 객체에 주입
- GameXxxServlet 변경
  - 생성자에서 List 객체를 받는 대신에 GameObjectFileDao 객체를 받는다.
  - 데이터를 저장하고, 조회하고, 변경하고, 삭제할 때 GameObjectFileDao 객체를 통해 처리.

### 훈련 4: 유저 데이터를 처리하는 DAO 클래스를 정의하고 적용

- julja.gms.UserObjectFileDao 클래스 정의
- julja.gms.DataLoaderListener 변경
  - 유저 데이터를 로딩하고 저장하는 기존 코드 제거
  - 대신에 UserObjectFileDao 객체 생성
- julja.gms.ServerApp 변경
  - Map에서 UserObjectFileDao를 꺼내 관련 커맨드 객체에 주입
- UserXxxServlet 변경
  - 생성자에서 List 객체를 받는 대신에 UserObjectFileDao 객체를 받는다.
  - 데이터를 저장하고, 조회하고, 변경하고, 삭제할 때 UserObjectFileDao 객체를 통해 처리
  
  