# jdbc_project_4team
사이드 프로젝트_4팀

# 프로젝트 구조 및 기능 정리

## 1. 프로젝트 구조 (Project Structure)

### 1.1 Java Source (`src/main/java`)

*   **`com.ohgiraffers.controller`**: 사용자 요청을 처리하는 서블릿 클래스
    *   **인증 관리**: `LoginServlet` (로그인), `RegistServlet` (회원가입), `DBResetServlet` (DB 초기화)
    *   **도서 관리**: `BookListServlet` (목록), `BookDetailServlet` (상세), `BookInsertServlet` (등록), `BookUpdateServlet` (수정), `BookDeleteServlet` (삭제)
*   **`com.ohgiraffers.service`**: 트랜잭션 및 비즈니스 로직 처리
    *   `UserService`: 회원 로그인, 가입 처리
    *   `BookService`: 도서 CRUD 로직
    *   `RecommendationService`: 시간대별 추천 도서 로직
*   **`com.ohgiraffers.dao`**: 데이터베이스 접근 객체 (Data Access Object)
    *   `UserDAO`: 회원 테이블 쿼리 실행
    *   `BookDAO`: 도서 테이블 쿼리 실행
*   **`com.ohgiraffers.dto`**: 데이터 전송 객체 (Data Transfer Object)
    *   `UserDTO`, `BookDTO`
*   **`com.ohgiraffers.common`**: 유틸리티
    *   `JDBCTemplate`: Connection 관리 (`commit`, `rollback`, `close`)
    *   `HangulUtils`: 한글 처리 도구
*   **`com.ohgiraffers.filter`**: 요청/응답 필터링
    *   `CharacterEncodingFilter`: UTF-8 인코딩 설정
    *   `PasswordEncryptFilter`: 비밀번호 암호화 처리 (Wrapper 사용)
    *   `PasswordRequestWrapper`: 암호화된 비밀번호를 요청에 래핑

### 1.2 Web Resources (`src/main/webapp`)

*   **Views (`/WEB-INF/views` & root)**:
    *   `index.jsp`: 메인 페이지 (추천 도서 노출)
    *   `regist.jsp`: 회원가입 폼
    *   `book/`: `list.jsp` (목록), `detail.jsp` (상세/수정/삭제)
*   **Config**: `WEB-INF/web.xml`, `resources/connection-info.properties`
*   **Static**: `resources/css`, `resources/images`, `resources/js`

## 2. 주요 기능 및 흐름 (Key Functions & Flow)

### 2.1 사용자 관리 (User Management)
*   **로그인**: ID/PW 입력 -> 필터에서 암호화 -> DB 비교 -> 세션 생성
*   **회원가입**: 정보 입력 -> 필터에서 암호화 -> DB 저장

### 2.2 도서 관리 (Book Management)
*   **목록 조회**: 전체 도서 목록 페이징/검색 조회
*   **상세 조회**: 특정 도서의 상세 정보 확인
*   **도서 등록/수정/삭제**: 관리자(또는 권한 있는 사용자) 기능

### 2.3 기타
*   **추천 서비스**: 매 시 정각마다 추천 도서 변경 로직 (`RecommendationService`)
*   **DB 리셋**: 초기 데이터로 복구 기능 제공

---

## 3. 시스템 흐름도 (System Flow Chart)

### 전체 아키텍처 흐름 (Architecture Flow)

```mermaid
graph TD
    User((사용자))
    
    subgraph View ["View (JSP)"]
        IndexPage[Main Page]
        AuthPage[Login/Regist Page]
        BookPage[Book List/Detail Page]
    end
    
    subgraph Filter ["Filters"]
        Encoding[Encoding Filter]
        Encrypt[Password Encrypt Filter]
    end
    
    subgraph Controller ["Controller (Servlet)"]
        LoginS[LoginServlet]
        RegistS[RegistServlet]
        BookS[Book Servlet]
    end
    
    subgraph Service ["Service Layer"]
        UserSv[UserService]
        BookSv[BookService]
    end
    
    subgraph DAO ["DAO Layer"]
        UserD[UserDAO]
        BookD[BookDAO]
    end
    
    DB[(Database)]

    User --> View
    View --> Encoding
    Encoding --> Encrypt
    Encrypt --> Controller
    
    Controller --> Service
    Service --> DAO
    DAO --> DB
```

### 상세 기능 프로세스 (Detailed Process)

#### 로그인 프로세스 (Login Process)
```mermaid
sequenceDiagram
    participant User
    participant Filter
    participant Servlet as LoginServlet
    participant Service as UserService
    participant DAO as UserDAO
    participant DB

    User->>Filter: 로그인 요청 (ID, PW)
    Filter->>Filter: PW 암호화
    Filter->>Servlet: 요청 전달 (Wrapper)
    Servlet->>Service: login(DTO) 호출
    Service->>DAO: loginResult(conn, DTO) 호출
    DAO->>DB: SELECT * FROM TBL_USER WHERE ID=? AND PW=?
    DB-->>DAO: 결과 반환
    DAO-->>Service: UserDTO 반환
    Service-->>Servlet: 로그인 성공 여부 반환
    
    alt 성공
        Servlet-->>User: 메인 페이지 리다이렉트 (Session)
    else 실패
        Servlet-->>User: 에러 페이지/메시지 표시
    end
```

#### 도서 목록 조회 프로세스 (Book List Process)
```mermaid
sequenceDiagram
    participant User
    participant Servlet as BookListServlet
    participant Service as BookService
    participant DAO as BookDAO
    participant DB

    User->>Servlet: 도서 목록 요청
    Servlet->>Service: selectBookList()
    Service->>DAO: selectAllBooks(conn)
    DAO->>DB: SELECT * FROM TBL_BOOK
    DB-->>DAO: ResultSet
    DAO-->>Service: List<BookDTO>
    Service-->>Servlet: List<BookDTO>
    Servlet-->>User: list.jsp (with data)
```

#### 도서 등록/수정/삭제 프로세스 (Book CRUD Process)

**1. 도서 등록 (Insert)**
```mermaid
sequenceDiagram
    participant User
    participant Servlet as BookInsertServlet
    participant Service as BookService
    participant DAO as BookDAO
    participant DB

    User->>Servlet: 도서 등록 요청 (POST)
    Servlet->>Service: registBook(BookDTO)
    Service->>DAO: insertBook(conn, BookDTO)
    DAO->>DB: INSERT INTO TBL_BOOK ...
    DB-->>DAO: 성공 여부 (int)
    DAO-->>Service: 성공 여부 (int)
    Service-->>Servlet: 성공 여부 (int)
    
    alt 성공
        Servlet-->>User: 목록 페이지로 리다이렉트
    else 실패
        Servlet-->>User: 에러 페이지/메시지
    end
```

**2. 도서 수정 (Update)**
```mermaid
sequenceDiagram
    participant User
    participant Servlet as BookUpdateServlet
    participant Service as BookService
    participant DAO as BookDAO
    participant DB

    User->>Servlet: 도서 수정 요청 (POST)
    Servlet->>Service: modifyBook(BookDTO)
    Service->>DAO: updateBook(conn, BookDTO)
    DAO->>DB: UPDATE TBL_BOOK SET ...
    DB-->>DAO: 성공 여부 (int)
    DAO-->>Service: 성공 여부 (int)
    Service-->>Servlet: 성공 여부 (int)
    
    alt 성공
        Servlet-->>User: 상세 페이지로 리다이렉트
    else 실패
        Servlet-->>User: 에러 페이지/메시지
    end
```

**3. 도서 삭제 (Delete)**
```mermaid
sequenceDiagram
    participant User
    participant Servlet as BookDeleteServlet
    participant Service as BookService
    participant DAO as BookDAO
    participant DB

    User->>Servlet: 도서 삭제 요청 (POST)
    Servlet->>Service: deleteBook(bookCode)
    Service->>DAO: deleteBook(conn, bookCode)
    DAO->>DB: DELETE FROM TBL_BOOK WHERE ...
    DB-->>DAO: 성공 여부 (int)
    DAO-->>Service: 성공 여부 (int)
    Service-->>Servlet: 성공 여부 (int)
    
    alt 성공
        Servlet-->>User: 목록 페이지로 리다이렉트
    else 실패
        Servlet-->>User: 에러 페이지/메시지
    end
```
