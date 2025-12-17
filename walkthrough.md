# 도서 데이터 생성 및 기능 구현 결과

## 1. 도서 데이터 및 이미지 생성
- 100권의 도서 데이터(`sql/book_data.sql`)와 SVG 플레이스홀더 이미지(`src/main/webapp/resources/images/`)를 생성했습니다.
- **적용 방법**: 데이터베이스에 SQL 파일을 실행하면 됩니다.

## 2. 시간별 도서 추천 기능
- **기능**: 메인 페이지(`index.jsp`)의 "Today's Pick" 섹션이 1시간마다 자동으로 변경됩니다.
- **구현**: `RecommendationService` 클래스가 현재 시간과 날짜를 기반으로 추천 도서를 선택합니다.

## 3. 코드 구조 개선 (Refactoring)
- **Service 계층 도입**: `com.ohgiraffers.service` 패키지를 신설했습니다.
    - `BookService`: 도서 관련 비즈니스 로직 처리.
    - `UserService`: 사용자 로그인 등 처리.
    - `RecommendationService`: 추천 알고리즘 처리.
- **Controller(Servlet) 개선**: Servlet에서 직접적인 DAO 호출 코드를 제거하고 Service를 통해 데이터를 요청하도록 수정하여 유지보수성을 높였습니다.

## 변경된 파일 목록
- `src/main/webapp/index.jsp`: 추천 도서 섹션 동적 처리 추가.
- `src/main/java/com/ohgiraffers/service/*`: `BookService.java`, `UserService.java`, `RecommendationService.java` 추가.
- `src/main/java/com/ohgiraffers/controller/*`: `LoginServlet.java`, `BookListServlet.java`, `BookDetailServlet.java` 수정.

## 검증 방법
- **추천 도서**: 메인 페이지 접속 시 이미지가 깨지지 않고 도서 정보가 잘 나오는지 확인. (시간이 지나면 바뀌는지 확인)
- **기존 기능**: 로그인, 도서 목록 조회, 상세 조회가 이전과 동일하게 잘 작동하는지 확인.
