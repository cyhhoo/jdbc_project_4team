# 구현 계획 - jdbc_4team_blueprint Web App 구축

`hanhwa-team4-java-project`의 기능을 참고하여, `jdbc_4team_blueprint` 디렉토리에 새로운 Web App을 구축합니다.

## 프로젝트 개요
- **목표**: 도서 관리 시스템의 Web App 버전 "초안(Blueprint)" 작성
- **경로**: `c:\SWCAMP22\team_project\jdbc_4team_blueprint`
- **스택**: Java 17+, Gradle, MariaDB, JDBC, Servlet(Tomcat), JSP/HTML/CSS

## 상세 구현 계획

### 1단계: 프로젝트 환경 구성
#### [NEW] `build.gradle`
- `war` 플러그인 적용.
- `jakarta.servlet-api`, `mariadb-java-client`, `jstl` 의존성 추가.

#### [NEW] 디렉토리 구조
```text
src/
  main/
    java/
      com/ohgiraffers/
        common/ (JDBCTemplate)
        dto/    (BookDTO)
        dao/    (BookDAO)
        controller/ (Servlet)
    resources/
    webapp/
      WEB-INF/
      views/
        book/
      resources/
        css/
      index.jsp
```

### 2단계: 데이터베이스 및 모델
#### [NEW] `sql/book_schema.sql`
- `tbl_book` 테이블 생성 쿼리.

#### [NEW] JAVA Classes
- `JDBCTemplate.java`: DB Connection 생성 및 close 처리.
- `BookDTO.java`: 기존 프로젝트의 DTO 구조 복사 및 정리.
- `BookDAO.java`: DB 연동 인터페이스 및 기본 조회 메소드.

### 3단계: 뷰 및 컨트롤러 (Read 기능)
#### [NEW] `BookListServlet.java`
- `/book/list` 요청 처리.
- DAO를 통해 목록 조회 후 `views/book/list.jsp`로 포워딩.

#### [NEW] `index.jsp` & `list.jsp`
- 메인 페이지 및 도서 목록 페이지.
- JSTL을 사용하여 데이터 출력.

#### [NEW] `style.css`
- 전체적인 레이아웃 및 테이블 스타일링.

### 4단계: CRUD 가이드
#### [NEW] `CRUD_Implementation_Guide.md`
- 사용자가 직접 구현할 Create, Update, Delete, 상세 조회에 대한 가이드 문서.
- DAO 메소드 시그니처 제안, Servlet 구조 제안, SQL 쿼리 예시 포함.

## 검증
- Gradle build 성공 확인.
- Tomcat 서버 구동 후 `localhost:8080/` 접속 확인.
