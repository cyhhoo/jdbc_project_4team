# CRUD 구현 가이드 (XML Mapper 방식)

이 문서는 **도서 관리 시스템**의 추가 기능 구현을 위한 가이드입니다.
SQL 쿼리를 자바 코드에 직접 작성하지 않고, `XML` 파일로 관리하는 방식으로 구현합니다.

## SQL 쿼리 관리 (`mapper/book-query.xml`)
모든 SQL 쿼리는 `src/main/resources/com/ohgiraffers/mapper/book-query.xml` 파일에 작성해야 합니다.

```xml
<entry key="insertBook">
    INSERT INTO tbl_book (title, author, price, image_url) VALUES (?, ?, ?, ?)
</entry>
```

## 1. 도서 등록 (Create)

1.  **쿼리 추가**: `book-query.xml`에 `insertBook` 키로 INSERT 쿼리를 추가하세요.
2.  **DAO 구현**: `BookDAO`의 `insertBook` 메소드에서 `prop.getProperty("insertBook")`으로 쿼리를 가져와 실행하세요.
    ```java
    String query = prop.getProperty("insertBook");
    pstmt = con.prepareStatement(query);
    // ... 파라미터 세팅
    ```
3.  **Servlet & JSP**: 이전 가이드와 동일하게 구현하세요 (이미지 업로드 포함).

## 2. 도서 수정 (Update)

1.  **쿼리 추가**: `book-query.xml`에 `updateBook` 키로 UPDATE 쿼리를 추가하세요.
2.  **DAO 구현**: `updateBook` 메소드 구현.

## 3. 도서 삭제 (Delete)

1.  **쿼리 추가**: `book-query.xml`에 `deleteBook` 키로 DELETE 쿼리를 추가하세요.
2.  **DAO 구현**: `deleteBook` 메소드 구현.

## 4. 기타
- `BookDAO` 생성자에서 이미 XML 파일을 로드하도록 설정되어 있습니다 (`prop.loadFromXML(...)`).
- 쿼리 키 값(key)을 정확하게 일치시켜야 합니다.
