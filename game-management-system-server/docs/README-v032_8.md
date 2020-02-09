# 32_8 - DAO 클래스의 공통점을 뽑에 수퍼 클래스로 정의하기(generalization 수행하기)

## 목표

- 상속의 기법 중 generalization을 이해
- generalization 구현

### 상속

- specialization
  - 수퍼 클래스를 상속 받아 특별한 기능을 수행하는 서브 클래스 만들기.
- generalization
  - 클래스들의 공통점을 뽑아 수퍼 클래스로 만든 후에 상속 관계 맺기.
 
## 소스 및 결과

- src/main/java/julja/gms/dao/AbstractObjectFileDao.java 추가
- src/main/java/julja/gms/dao/BoardObjectFileDao.java 변경
- src/main/java/julja/gms/dao/LessonObjectFileDao.java 변경
- src/main/java/julja/gms/dao/MemberObjectFileDao.java 변경
- src/main/java/julja/gms/ServerApp.java 변경


### 1: DAO의 공통점을 뽑아 수퍼 클래스로 정의하라.

- julja.gms.dao.AbstractObjectFileDao 클래스를 생성한다.

### 2: BoardObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경하라.

- julja.gms.dao.BoardObjectFileDao 변경한다.
  