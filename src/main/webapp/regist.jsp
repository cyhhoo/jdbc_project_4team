<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="ko">

    <head>
        <meta charset="UTF-8">
        <title>회원가입</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    </head>

    <body>
        <header>
            <div class="container header-content">
                <h1>도서 예약 시스템</h1>
            </div>
        </header>
        <main class="container">
            <div class="login-container">
                <h2>회원가입</h2>
                <form action="${pageContext.request.contextPath}/regist" method="post" class="login-form">
                    <div class="form-group">
                        <label for="userId">아이디</label>
                        <input type="text" id="userId" name="userId" required placeholder="아이디를 입력하세요">
                    </div>
                    <div class="form-group">
                        <label for="userPwd">비밀번호</label>
                        <input type="password" id="userPwd" name="userPwd" required placeholder="비밀번호를 입력하세요">
                    </div>
                    <div class="form-group">
                        <label for="userName">이름</label>
                        <input type="text" id="userName" name="userName" required placeholder="이름을 입력하세요">
                    </div>
                    <button type="submit" class="btn-login">가입하기</button>
                    <div style="margin-top: 10px; text-align: center;">
                        <a href="${pageContext.request.contextPath}/index.jsp"
                            style="color: #666; text-decoration: none;">취소하고 돌아가기</a>
                    </div>
                </form>
            </div>
        </main>
        <footer>
            <p>&copy; Team 4. 강성훈, 박찬진, 손창우, 최연후 </p>
        </footer>
    </body>

    </html>