<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <title>도서 등록</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <style>
        .form-container {
            max-width: 600px;
            margin: 2rem auto;
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        .form-group label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: 500;
            color: var(--text-primary);
        }

        .form-control {
            width: 100%;
            padding: 0.75rem;
            border: 1px solid var(--border-color);
            border-radius: 4px;
            font-size: 1rem;
            box-sizing: border-box; /* 패딩이 너비에 영향을 주지 않도록 설정 */
        }

        .form-control:focus {
            outline: none;
            border-color: var(--primary-color);
            box-shadow: 0 0 0 2px rgba(var(--primary-rgb), 0.1);
        }

        .btn-group {
            display: flex;
            gap: 10px;
            margin-top: 2rem;
        }

        .btn-submit {
            flex: 2;
        }

        .btn-cancel {
            flex: 1;
            background-color: #eee;
            color: #333;
            text-align: center;
            text-decoration: none;
            line-height: 2.5rem;
            border-radius: 4px;
        }
    </style>
</head>

<body>
<header>
    <div class="container header-content">
        <h1><a href="${pageContext.request.contextPath}/" style="text-decoration: none; color: inherit;">도서 관리 시스템</a></h1>
    </div>
</header>

<c:if test="${not empty requestScope.message}">
    <script>
        alert('${requestScope.message}');
    </script>
</c:if>

<main class="container">
    <div class="card form-container">
        <div style="margin-bottom: 2rem;">
            <h2>새로운 도서 등록하기</h2>
            <p style="color: var(--text-secondary); font-size: 0.875rem;">추가할 도서의 상세 정보를 입력해주세요.</p>
        </div>

        <form action="${pageContext.request.contextPath}/book/insert" method="post">

            <div class="form-group">
                <label for="title">도서 제목</label>
                <input type="text" id="title" name="title" class="form-control" placeholder="도서 명을 입력하세요" required>
            </div>

            <div class="form-group">
                <label for="author">저자</label>
                <input type="text" id="author" name="author" class="form-control" placeholder="작가 명을 입력하세요" required>
            </div>

            <div class="form-group">
                <label for="price">가격 (원)</label>
                <input type="number" id="price" name="price" class="form-control" placeholder="가격을 입력하세요 (숫자만)" required min="0">
            </div>

            <div class="form-group">
                <label for="imageUrl">이미지 URL</label>
                <input type="text" id="imageUrl" name="imageUrl" class="form-control" placeholder="이미지 주소를 입력하세요 (http://...)">
            </div>

            <div class="btn-group">
                <button type="submit" class="btn btn-primary btn-submit">등록하기</button>
                <a href="${pageContext.request.contextPath}/book/list" class="btn-cancel">취소</a>
            </div>

        </form>
    </div>
</main>

<footer>
    <p>&copy; Team 4. 강성훈, 박찬진, 손창우, 최연후 </p>
</footer>
</body>

</html>