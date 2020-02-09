# 32_9 - 파일에 데이터를 저장할 때 JSON 형식 사용

## 목표

- JSON(JavaScript Object Notation) 형식 이해
- Gson 라이브러리를 이용하여 JSON 형식의 데이터 다루기

## 소스 및 결과

- src/main/java/julja/gms/dao/json 패키지 추가
- src/main/java/julja/gms/dao/json/AbstractJsonFileDao.java 추가
- src/main/java/julja/gms/dao/json/BoardJsonFileDao.java 변경
- src/main/java/julja/gms/dao/json/GameJsonFileDao.java 변경
- src/main/java/julja/gms/dao/json/UserJsonFileDao.java 변경
- src/main/java/julja/gms/servlet/BoardXxxServlet.java 변경
- src/main/java/julja/gms/servlet/GameXxxServlet.java 변경
- src/main/java/julja/gms/servlet/UserXxxServlet.java 변경
- src/main/java/julja/gms/DataLoaderListener.java 변경
- src/main/java/julja/gms/ServerApp.java 변경

### 1: JSON 형식으로 데이터를 저장하고 로딩할 수퍼 클래스 정의

- julja.gms.dao.json 패키지 생성
- julja.gms.dao.json.AbstractJsonFileDao 클래스 생성

### 2: BoardObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경

- julja.gms.dao.BoardObjectFileDao 변경
  - 상속 받은 필드와 메서드 사용
  - indexOf()를 오버라이딩

### 3: GameObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경.

- julja.gms.dao.GameObjectFileDao 변경
  - 상속 받은 필드와 메서드 사용
  - indexOf()를 오버라이딩

### 4: UserObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경

- julja.gms.dao.UserObjectFileDao 변경
  - 상속 받은 필드와 메서드를 사용
  - indexOf()를 오버라이딩

### 5: XxxObjectFileDao 대신 XxxJsonFileDao를 사용하도록 서블릿 클래스 변경

- julja.gms.servlet.BoardXxxServlet 변경
- julja.gms.servlet.GameXxxServlet 변경
- julja.gms.servlet.UserXxxServlet 변경

### 6: 애플리케이션이 시작할 때 XxxObjectFileDao 대신 XxxJsonFileDao 준비

- julja.gms.DataLoaderListener 변경

### 7: XxxObjectFileDao 대신 XxxJsonFileDao를 서블릿 객체에 주입

- julja.gms.ServerApp 변경
 
  