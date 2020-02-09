# 32_10 - 인터페이스를 이용하여 DAO 호출 규칙을 통일

## 목표

- 인터페이스 문법의 존재 이유(사용 목적)
- 인터페이스를 정의하고 구현

### 인터페이스

- 사용자(예: Servlet)와 피사용자(예: DAO) 사이에 호출 규칙을 정의하는 문법.
- 호출 규칙을 정의해 두면 사용자 입장에서 피사용자측이 다른 객체로 바뀌더라도 
  코드를 변경할 필요가 없기 때문에 유지보수 유리
- 피사용자 객체를 정의하는 개발자 입장에서도 인터페이스 규칙에 따라 만들면 되기 때문에 
  코드 작성과 테스트 용이


## 실습 소스 및 결과

- src/main/java/julja/gms/dao/BoardDao.java 인터페이스 추가
- src/main/java/julja/gms/dao/BoardObjectFileDao.java 변경
- src/main/java/julja/gms/dao/json/BoardJsonFileDao.java 변경
- src/main/java/julja/gms/dao/GameDao.java 인터페이스 추가
- src/main/java/julja/gms/dao/GameObjectFileDao.java 변경
- src/main/java/julja/gms/dao/json/GameJsonFileDao.java 변경
- src/main/java/julja/gms/dao/UserDao.java 인터페이스 추가
- src/main/java/julja/gms/dao/UserObjectFileDao.java 변경
- src/main/java/julja/gms/dao/json/UserJsonFileDao.java 변경
- src/main/java/julja/gms/servlet/BoardXxxServlet.java 변경
- src/main/java/julja/gms/servlet/GameXxxServlet.java 변경
- src/main/java/julja/gms/servlet/UserXxxServlet.java 변경
- src/main/java/julja/gms/DataLoaderListener.java 변경
- src/main/java/julja/gms/ServerApp.java 변경

## 실습  

### 1: BoardXxxServlet이 사용할 DAO 호출 규칙을 정의하고 구현

- julja.gms.dao.BoardDao 인터페이스 생성
- julja.gms.dao.BoardObjectFileDao 클래스를 변경
  - BoardDao 인터페이스를 구현
- julja.gms.dao.json.BoardJsonFileDao 클래스를 변경
  - BoardDao 인터페이스를 구현
- julja.gms.servlet.BoardXxxServlet 클래스를 변경
  - DAO 레퍼런스 타입을 BoardDao 인터페이스로 변경
- julja.gms.DataLoaderListener 변경
- julja.gms.ServerApp 변경
 
### 2: GameXxxServlet이 사용할 DAO 호출 규칙을 정의하고 구현하라.

- julja.gms.dao.GameDao 인터페이스 생성한다.
- julja.gms.dao.GameObjectFileDao 클래스를 변경한다.
  - GameDao 인터페이스를 구현한다.
- julja.gms.dao.json.GameJsonFileDao 클래스를 변경한다.
  - GameDao 인터페이스를 구현한다.
- julja.gms.servlet.GameXxxServlet 클래스를 변경한다.
  - DAO 레퍼런스 타입을 GameDao 인터페이스로 변경한다.
- julja.gms.DataLoaderListener 변경한다.
- julja.gms.ServerApp 변경한다.

### 3: UserXxxServlet이 사용할 DAO 호출 규칙을 정의하고 구현

- julja.gms.dao.UserDao 인터페이스 생성
- julja.gms.dao.UserObjectFileDao 클래스를 변경
  - UserDao 인터페이스를 구현
- julja.gms.dao.json.UserJsonFileDao 클래스를 변경
  - UserDao 인터페이스를 구현
- julja.gms.servlet.UserXxxServlet 클래스를 변경
  - DAO 레퍼런스 타입을 UserDao 인터페이스로 변경
- julja.gms.DataLoaderListener 변경
- julja.gms.ServerApp 변경
  