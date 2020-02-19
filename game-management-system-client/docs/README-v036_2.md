# 36_2 - DB 커넥션 객체 공유하기

## 목표

- 한 개의 DB 커넥션을 여러 DAO에서 공유

## 소스 및 결과

- src/main/java/julja/gms/dao/mariadb/BoardDaoImpl.java 변경
- src/main/java/julja/gms/dao/mariadb/LessonDaoImpl.java 변경
- src/main/java/julja/gms/dao/mariadb/MemberDaoImpl.java 변경
- src/main/java/julja/gms/ClientApp.java 변경

### 한 개의 DB 커넥션 객체를 DAO 모두가 공유

- julja.gms.ClientApp 변경
- julja.gms.dao.mariadb.XxxDaoImpl 변경

