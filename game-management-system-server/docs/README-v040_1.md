# 40_1 - Connection을 ThreadLocal을 사용하여 스레드에 보관하기

## 소스 및 결과

- src/main/java/julja/util/ConnectionFactory.java 추가
- src/main/java/julja/gms/DataLoaderListener.java 변경
- src/main/java/julja/gms/ServerApp.java 변경

### 1: 커넥션 팩토리에서 생성한 Connection 객체를 스레드에 보관

- julja.util.ConnectionFactory 변경
  - getConnection() 변경
    - 스레드에 보관된 Connection 객체가 없다면, 새로 생성하여 리턴
    - 새로 생성한 Connection 객체는 스레드에 보관
    - 스레드에 보관된 Connection 객체가 있다면 그 객체를 꺼내 리턴
    
#### 문제점

- 현재 스레드풀(ExecutorService)을 이용하여 스레드 관리
- DAO 메서드에서 Connection을 사용한 후에 닫기 때문에, 다음 클라이언트 요청을 처리하기 위해 스레드를 재사용할 때 오류 발생

#### 해결책

- 클라이언트에게 응답을 완료한 후에 스레드에 보관된 Connection 객체를 제거
- 다음에 클라이언트 요청을 처리하기 위해 같은 스레드가 사용되더라도 이미 그 스레드에는 Connection 객체가 없기 때문에 ConnectionFactory는 새 Connection 만들어 리턴
  
### 2: 클라이언트에 응답을 한 후 스레드에 보관된 Connection 객체를 제거

- julja.util.ConnectionFactory 변경
  - Thread에 보관된 Connection 객체를 제거하는 메서드를 추가
  - removeConnection();
- julja.gms.DataLoaderListener 변경
  - ServerApp에서 ConnectionFactory를 사용할 수 있도록 맵에 보관하여 리턴
- julja.gms.ServerApp 변경
  - 클라이언트 요청을 처리한 후에 Thread에서 Connection 제거