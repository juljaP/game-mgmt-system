# 42_1 - 사용자 로그인 기능 추가

## 소스 및 결과

- src/main/java/julja/gms/dao/UserDao.java 변경
- src/main/java/julja/gms/dao/mariadb/UserDaoImpl.java 변경
- src/main/java/julja/gms/servlet/LoginServlet.java 추가
- src/main/java/julja/gms/ServerApp.java 변경

### 1: 사용자 로그인 기능 추가

- gms_user 테이블의 암호 초기화
  - 테스트하기 위해 모든 회원의 암호를 '1111'로 초기화 
  - update gms_user set pwd=password('1111') 실행
- julja.gms.dao.UserDao 변경
  - 이메일과 암호를 가지고 사용자를 조회하는 메서드를 추가
  - User findByEmailAndPassword(String email, String password)
- julja.gms.dao.mariadb.UserDaoImpl 변경
  - UserDao에 추가한 메서드를 구현
  - insert(), update()의 SQL 문에서 암호를 입력하거나 변경할 때 
    password() SQL 함수로 인코딩하도록 SQL 문 변경
- julja.gms.servlet.LoginServlet 추가
  - 사용자로부터 이메일과 암호를 입력받아 인증 수행
- julja.gms.ServerApp 변경
  - "/auth/login" 명령 처리할 LoginServlet 객체를 맵에 추가
  
'ClientApp' 실행 예:
```
명령> /auth/login
이메일?
user1@test.com
암호?
1111
'홍길동'님 환영합니다.

명령> /auth/login
이메일?
user1@test.com
암호?
2222
사용자 정보가 유효하지 않습니다.
```

### 2: SQL 삽입 공격 통해 유효하지 않은 사용자 정보로 로그인 

'ClientApp' 실행 예:
```
명령> /auth/login
이메일?
user3@test.com
암호?
aaa') or (email='user3@test.com' and 'a'='a
'user3'님 환영합니다.

```






