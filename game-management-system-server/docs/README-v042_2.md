# 42_2 - SQL 삽입 공격과 자바 시큐어 코딩: PreparedStatement로 전환

## 소스 및 결과

- src/main/java/julja/gms/dao/mariadb/XxxDaoImpl.java 변경


### 1: Statement -> PreparedStatement

- julja.gms.dao.mariadb.XxxDaoImpl 변경
  - Statement를 PreparedStatement 로 변경

### 2: SQL 삽입 공격을 통해 유효하지 않은 사용자 정보로 로그인

'ClientApp' 실행 예:
```
명령> /auth/login
이메일?
user3@test.com
암호?
aaa') or (email='user3@test.com' and 'a'='a
사용자의 정보가 유효하지 않습니다.

```