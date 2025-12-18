<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>도서 수정</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

<header>
    <div class="container header-content">
        <h1>도서 수정</h1>
        <div class="user-info">
            <button class="btn-logout" onclick="location.href='${pageContext.request.contextPath}/'">메인</button>
        </div>
    </div>
</header>

<main class="container" style="max-width: 720px; margin-top: 2rem;">

    <c:if test="${empty book}">
        <script>alert('수정할 도서 정보를 불러오지 못했습니다.');</script>
        <div style="text-align:center;">
            <a class="btn" href="${pageContext.request.contextPath}/book/list">목록으로</a>
        </div>
    </c:if>

    <c:if test="${not empty book}">
        <form action="${pageContext.request.contextPath}/book/update" method="post" class="login-form">
            <!-- 수정 대상 지정용 (필수) -->
            <input type="hidden" name="bookId" value="${book.bookId}"/>

            <div class="form-group">
                <label>도서 번호</label>
                <input type="text" value="${book.bookId}" readonly>
            </div>

            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" id="title" name="title" value="${book.title}" required>
            </div>

            <div class="form-group">
                <label for="author">저자</label>
                <input type="text" id="author" name="author" value="${book.author}" required>
            </div>

            <div class="form-group">
                <label for="price">가격</label>
                <input type="number" id="price" name="price" value="${book.price}" min="0" required>
            </div>

            <div class="form-group">
                <label for="imageUrl">이미지 URL</label>
                <input type="text" id="imageUrl" name="imageUrl" value="${book.imageUrl}">
                <small style="display:block; margin-top:6px; color: var(--text-secondary);">
                    예) /resources/images/book.png 또는 https://...
                </small>
            </div>

            <c:if test="${not empty book.imageUrl}">
                <div style="margin: 1rem 0; text-align:center;">
                    <img src="${pageContext.request.contextPath}${book.imageUrl}"
                         alt="도서 이미지"
                         style="max-height:220px; border-radius:12px;"
                         onerror="this.src='https://via.placeholder.com/150x220?text=No+Image'">
                </div>
            </c:if>

            <div style="display:flex; gap:10px; justify-content:flex-end; margin-top: 1.5rem;">
                <button type="submit" class="btn-login">수정 저장</button>
                <button type="button" class="btn-logout"
                        onclick="location.href='${pageContext.request.contextPath}/book/detail?bookId=${book.bookId}'">
                    취소
                </button>
            </div>
        </form>
    </c:if>

</main>

<footer>
    <p>&copy; Team 4. 강성훈, 박찬진, 손창우, 최연후 </p>
</footer>

</body>
</html>
