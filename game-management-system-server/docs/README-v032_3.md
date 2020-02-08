# 32_3 - LMS 관리 데이터를 파일에서 로딩하고, 파일로 저장하기

## 목표

- 데이터 파일 읽고 쓰기

## 실습 소스 및 결과

- src/main/java/julja/gms/domain 패키지 생성
- src/main/java/julja/gms/domain/Board.java 추가
- src/main/java/julja/gms/domain/Lesson.java 추가
- src/main/java/julja/gms/domain/Member.java 추가
- src/main/java/julja/gms/context 패키지 생성
- src/main/java/julja/gms/context/ApplicationContextListener.java 추가
- src/main/java/julja/gms/DataLoaderListener.java 추가
- src/main/java/julja/gms/ServerApp.java 변경

### 1: 31번 프로젝트에서 데이터를 저장하고 로딩하는 코드 가져오기

- julja.gms.domain 패키지 생성
- julja.gms.damain.* 가져오기
- julja.gms.context 패키지 생성
- julja.gms.context.ApplicationContextListener 클래스 가져오기
- julja.gms.DataLoaderListener 가져오기
- ServerApp.java 변경
  - 옵저버 등록, 호출 코드 적용