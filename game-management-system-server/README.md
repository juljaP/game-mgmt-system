# 32_5 - 특정 기능을 수행하는 코드를 메서드로 분리

## 목표

- 기능 별로 코드를 메서드로 분리
- 분리한 메서드 사용
- "Extract Method" 리팩토링 기법 이해

## 소스 및 결과

- src/main/java/julja/gms/ServerApp.java 변경

### 1: 클라이언트의 요청을 처리하는 코드를 기능별로 분리

- ServerApp.java 변경
  - if~ else~ 분기문에 작성한 코드를 별도의 메서드로 분리하여 정의
  - listBoard() : 게시물 목록 데이터 요청 처리
  - addBoard() : 게시물 데이터 등록 요청 처리
  - detailBoard() : 게시물 조회 요청 처리
  - updateBoard() : 게시물 변경 요청 처리
  - deleteBoard() : 게시물 삭제 요청 처리
  - listUser() : 유저 목록 데이터 요청 처리
  - addUser() : 유저 데이터 등록 요청 처리
  - detailUser() : 유저 조회 요청 처리
  - updateUser() : 유저 변경 요청 처리
  - deleteUser() : 유저 삭제 요청 처리
  - listGame() : 게임 목록 데이터 요청 처리
  - addGame() : 게임 데이터 등록 요청 처리
  - detailGame() : 게임 조회 요청 처리
  - updateGame() : 게임 변경 요청 처리
  - deleteGame() : 게임 삭제 요청 처리      
