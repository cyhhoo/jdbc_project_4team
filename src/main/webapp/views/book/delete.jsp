<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>도서 삭제 확인</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <style>
        .delete-container {
            max-width: 600px;
            margin: 2rem auto;
            background: white;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .delete-container h2 {
            font-size: 1.8rem;
            margin-bottom: 1rem;
            color: var(--text-primary);
        }
        .book-info {
            background-color: #f3f4f6;
            padding: 1rem;
            border-radius: 8px;
            margin: 2rem 0;
            text-align: left;
        }
        .info-row {
            margin-bottom: 0.5rem;
        }
        .label {
            font-weight: 600;
            color: var(--text-secondary);
            margin-right: 0.5rem;
        }
        .btn-group {
            display: flex;
            gap: 1rem;
            justify-content: center;
            margin-top: 2rem;
        }
        .alert-danger {
            color: #721c24;
            background-color: #f8d7da;
            border-color: #f5c6cb;
            padding: .75rem 1.25rem;
            margin-bottom: 1rem;
            border: 1px solid transparent;
            border-radius: .25rem;
        }
    </style>
</head>
<body>
    <header>
        <div class="container header-content">
            <h1><a href="${pageContext.request.contextPath}/" style="text-decoration: none; color: inherit;">도서 관리 시스템</a></h1>
            <c:if test="${not empty sessionScope.loginUser}">
                <div class="user-info">
                    <span>${sessionScope.loginUser.name}님</span>
                </div>
            </c:if>
        </div>
    </header>

    <main class="container">
        <div class="delete-container">
            <h2>도서 삭제</h2>

         <%--   <c:if test="${not empty requestScope.error}">
                <div class="alert alert-danger">
                    <p>${requestScope.error}</p>
                </div>
            </c:if>--%>

            <p>삭제할 도서의 id를 입력하세요.</p>

            <form action="/book/delete" method="post">
                <label>삭제할 도서 id : </label>
                <input type="number" name="bookId" placeholder="0" required>
                <button>삭제하기</button>
            </form>

           <%-- <c:if test="${not empty requestScope.book}">
                <div class="book-info">
                    <div class="info-row"><span class="label">도서 번호:</span> ${requestScope.book.bookId}</div>
                    <div class="info-row"><span class="label">제목:</span> ${requestScope.book.title}</div>
                    <div class="info-row"><span class="label">저자:</span> ${requestScope.book.author}</div>
                </div>

                <div class="btn-group">
                    <a href="${pageContext.request.contextPath}/delete?id=${requestScope.book.bookId}" class="btn btn-delete">삭제</a>
                    <a href="${pageContext.request.contextPath}/book/detail?id=${requestScope.book.bookId}" class="btn" style="background-color: #e5e7eb; color: var(--text-primary);">취소</a>
                </div>
            </c:if>--%>

           <%-- <c:if test="${empty requestScope.book}">
                 <div class="book-info">
                    <p>삭제할 도서 정보를 불러오지 못했습니다.</p>
                 </div>
                 <div class="btn-group">
                    <a href="${pageContext.request.contextPath}/book/list" class="btn">목록으로 돌아가기</a>
                </div>
            </c:if>--%>

            <div class="btn-group">
                <a href="${pageContext.request.contextPath}/book/list" class="btn">목록으로 돌아가기</a>
            </div>

        </div>
    </main>

    <footer>
        <p>&copy; Team 4. 강성훈, 박찬진, 손창우, 최연후</p>
    </footer>
</body>
</html>
