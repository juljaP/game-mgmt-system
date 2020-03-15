# 45_2 - Mybatis를 이용하여 DAO 구현체 자동 생성

- Mybatis의 SqlSession을 이용하여 DAO 구현체 자동으로 생성
- Mybatis의 DAO 자동 생성기

## 소스 및 결과

- src/main/java/julja/gms/service/impl/BoardServiceImpl2.java 추가
- src/main/java/julja/gms/DataLoaderListener.java 변경


### 1: BoardServiceImpl 에 대해서 Mybatis DAO 자동 생성기 적용

- julja.gms.service.impl.BoardServiceImpl2 추가
  - BoardDao 구현체를 직접 주입하는 대신에 SqlSessionFactory 주입
  - BoardDao를 사용할 때 마다 SqlSession 객체를 통해 만들어 쓴다. 
  
- julja.gms.DataLoaderListener 변경
  - BoardService 구현체를 BoardServiceImpl2로 교체
