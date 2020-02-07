# 32_4 - 클라이언트의 데이터 관리 요청 처리

- 클라이언트의 요청 받기
- 클라이언트의 요청에 대해 데이터 보내기

## 실습 소스 및 결과

- src/main/java/julja/gms/ServerApp.java 변경


### 1: 서비스를 시작할 때 클라이언트의 연결을 기다리는 코드 추가

- ServerApp.java 변경
  - ServerSocket 준비
  - 클라이언트 연결 준비
  
### 2: 클라이언트의 게시물 목록 요청(/board/list) 처리

- ServerApp.java 변경
  - processRequest() 메서드 변경
- ServerAppTest.java 추가
  - 서버의 응답 기능을 테스트
- Board.java 변경
  - 통신 테스트 할 때 게시물 필드 정보를 확인할 수 있도록 toString() 오버라이딩

### 3: 클라이언트의 게시물 등록 요청(/board/add) 처리

- ServerApp.java 변경
  - processRequest() 메서드 변경

### 4: 클라리언트의 게시물 조회 요청(/board/detail) 처리

- ServerApp.java 변경
  - processRequest() 메서드 변경
  
### 훈련 5: 클라리언트의 게시물 변경 요청(/board/detail) 처리

- ServerApp.java 변경
  - processRequest() 메서드 변경  
  
### 훈련 6: 클라리언트의 게시물 삭제 요청(/board/detail) 처리

- ServerApp.java 변경
  - processRequest() 메서드 변경  

### 훈련 7: 클라리언트의 수업 관리 요청(/lesson/*) 처리

- ServerApp.java 변경
  - processRequest() 메서드 변경  
  
### 훈련 8: 클라리언트의 회원 관리 요청(/member/*) 처리

- ServerApp.java 변경
  - processRequest() 메서드 변경  