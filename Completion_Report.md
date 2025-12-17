# Web App 구축 완료 보고서 (최종)

요청하신 `jdbc_4team_blueprint` 기반의 Web App 구축이 완료되었습니다.

## 1. 구현 내용 요약
- **프로젝트 위치**: `c:\SWCAMP22\team_project\jdbc_4team_blueprint\jdbc_4team`
- **기술 스택**: Java 17, Gradle(War), MariaDB, JDBC, Servlet, JSP, CSS
- **주요 기능**:
    - [x] 데이터베이스 연결 (`JDBCTemplate`) & 스키마 설정 (`image_url` 포함)
    - [x] **SQL 쿼리 분리 (`mapper/book-query.xml`)**: 유지보수성을 위해 SQL을 XML로 관리합니다.
    - [x] 도서 목록 조회 (Read) & 이미지 썸네일 표시
    - [x] 파일 업로드 준비 (`resources/images` 폴더)

## 2. 사용 방법

### 데이터베이스 설정
`sql/book_schema.sql`을 실행하여 테이블(`tbl_book`)을 생성/업데이트하세요.

### 쿼리 수정 방법
`src/main/resources/com/ohgiraffers/mapper/book-query.xml` 파일을 열어 SQL을 수정하거나 추가할 수 있습니다.
자바 코드를 수정하지 않고 쿼리만 변경 가능합니다.

## 3. CRUD 구현 가이드 (XML 방식)
**[CRUD 구현 가이드 (XML Mapper)](file:///c:/Users/user/.gemini/antigravity/brain/cbc3ec2b-ed06-4dbf-9cfe-1703a2ec1d14/CRUD_Implementation_Guide.md)**
위 문서를 참고하여 추가 기능(INSERT, UPDATE, DELETE)을 구현할 때 XML에 쿼리를 등록하여 사용하세요.
