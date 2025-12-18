# 프로젝트 구조 및 기능 요약

## 1. 프로젝트 구조

### 1.1. `src/main/java` - Java 소스 코드
- **`com.ohgiraffers.controller`**: 서블릿 클래스들이 위치합니다.
  - `LoginServlet`: 로그인 처리
  - `RegistServlet`: 회원가입 처리
  - `DBResetServlet`: 데이터베이스 초기화
  - `BookListServlet`: 도서 목록 조회
  - `BookDetailServlet`: 도서 상세 정보 조회
- **`com.ohgiraffers.service`**: 비즈니스 로직을 담당하는 서비스 클래스들이 위치합니다.
  - `BookService`: 도서 관련 비즈니스 로직
  - `UserService`: 사용자 관련 비즈니스 로직
  - `RecommendationService`: 추천 관련 비즈니스 로직
- **`com.ohgiraffers.dao`**: 데이터베이스와 상호작용하는 DAO 클래스들이 위치합니다.
  - `BookDAO`: 도서 정보에 대한 CRUD
  - `UserDAO`: 사용자 정보에 대한 CRUD
- **`com.ohgiraffers.dto`**: 데이터 전송 객체(DTO) 클래스들이 위치합니다.
  - `BookDTO`: 도서 정보를 담는 객체
  - `UserDTO`: 사용자 정보를 담는 객체
- **`com.ohgiraffers.common`**: 공통으로 사용되는 유틸리티 클래스들이 위치합니다.
  - `JDBCTemplate`: 데이터베이스 연결 및 자원 해제
  - `HangulUtils`: 한글 처리 유틸리티
- **`com.ohgiraffers.filter`**: 서블릿 필터 클래스들이 위치합니다.
  - `CharacterEncodingFilter`: 문자 인코딩 설정
  - `PasswordEncryptFilter`: 비밀번호 암호화

### 1.2. `src/main/webapp` - 웹 애플리케이션 파일
- **`views`**: JSP 파일들이 위치합니다.
  - `book/list.jsp`: 도서 목록 페이지
  - `book/detail.jsp`: 도서 상세 정보 페이지
- **`WEB-INF`**: 웹 애플리케이션 설정 파일이 위치합니다.
  - `web.xml`: 서블릿 및 필터 매핑 설정
- **`resources`**: CSS, JavaScript, 이미지 등 정적 파일들이 위치합니다.
- `index.jsp`: 메인 페이지
- `regist.jsp`: 회원가입 페이지

### 1.3. `src/main/resources` - 리소스 파일
- **`com/ohgiraffers/mapper`**: SQL 쿼리 XML 파일이 위치합니다.
  - `book-query.xml`: 도서 관련 SQL 쿼리
  - `user-query.xml`: 사용자 관련 SQL 쿼리
- `connection-info.properties`: 데이터베이스 연결 정보

### 1.4. `sql` - SQL 스크립트
- `user_schema.sql`: 사용자 테이블 생성 스크립트
- `book_schema.sql`: 도서 테이블 생성 스크립트
- `book_data.sql`: 도서 데이터 추가 스크립트

## 2. 주요 기능

- **사용자 관리**: 회원가입 및 로그인 기능을 제공합니다.
- **도서 정보**: 도서 목록 조회 및 상세 정보 조회 기능을 제공합니다.
- **데이터베이스 연동**: JDBC를 사용하여 데이터베이스와 연동하고, `JDBCTemplate`을 통해 연결 및 자원 관리를 효율적으로 처리합니다.
- **MVC 패턴**: 서블릿을 컨트롤러로, JSP를 뷰로, 서비스와 DAO를 모델로 하는 MVC 패턴을 따릅니다.
- **필터**: 문자 인코딩 및 비밀번호 암호화 처리를 위해 서블릿 필터를 사용합니다.
- **데이터베이스 초기화**: `DBResetServlet`을 통해 데이터베이스를 초기 상태로 리셋할 수 있습니다.
