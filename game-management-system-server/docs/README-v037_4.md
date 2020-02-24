# 37_4 - Application Server : 검색 기능 추가 

- Application Server 아키텍처의 구성과 특징
- 통신 프로토콜 규칙에 따라 동작하는 서버

## 소스 및 결과

- src/main/java/julja/gms/dao/UserDao.java 변경
- src/main/java/julja/gms/dao/mariadb/UserDaoImpl.java 변경
- src/main/java/julja/gms/servlet/UserSearchServlet.java 추가
- src/main/java/julja/gms/ServerApp.java 변경 

### 1: 유저 검색 기능

- julja.gms.dao.UserDao 변경
  - findByKeyword() 메서드 추가
- julja.gms.dao.mariadb.UserDaoImpl 변경
  - findByKeyword() 메서드 구현
- julja.gms.servlet.UserSearchServlet 추가
  - 클라이언트에게 검색 키워드 요청
  - 클라이언트가 보낸 키워드를 가지고 회원 검색
  - 검색한 결과를 가지고 출력 내용 생성
  - 클라이언트에게 보내기
- julja.gms.ServerApp 변경
  - '/member/search' 명령을 처리할 UserSearchServlet 객체 등록
  