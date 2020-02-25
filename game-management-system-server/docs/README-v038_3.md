# 38_3 - 트랜잭션 적용 전: 코드 리팩토링

- 여러 개의 DB 변경 작업 한 작업 단위로 묶는 트랜잭션
- `commit`과 `rollback` 활용
- 반복적으로 사용되는 코드 별도의 메서드로 분리
- 공통 코드 별도의 클래스로 분리

## 소스 및 결과

- src/main/java/julja/util/Prompt.java 추가
- src/main/java/julja/servlet/XxxServlet.java 변경

### 1: 클라이언트에게 입력 값 요구하는 코드 리팩토링 

- julja.util.Prompt 추가
  - 입력 값 요구하는 코드 메서드로 정의
  - getXxx() 메서드 정의.
- julja.gms.servlet.XxxServlet 변경
  - 입력 값 요구하는 코드 Prompt.getXxx() 호출로 대체

### 2: 첨부파일 입력 코드 리팩토링

- julja.gms.servlet.PhotoBoardAddServlet 변경
  - 첨부파일 입력 부분 별도의 메서드로 분리
- julja.gms.servlet.PhotoBoardUpdateServlet 변경
  - 첨부파일 목록 출력하는 부분 별도의 메서드로 분리
  - 첨부파일 입력 부분 별도의 메서드로 분리
