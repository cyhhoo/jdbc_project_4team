<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"
    import="com.ohgiraffers.service.BookService" import="com.ohgiraffers.dto.BookDTO" import="java.util.List"
    import="java.util.ArrayList" %>
    <%@ page import="java.awt.print.Book" %>

        <%@ taglib prefix="c" uri="jakarta.tags.core" %>
            <!DOCTYPE html>
            <html lang="ko">

            <head>
                <meta charset="UTF-8">
                <title>도서 예약 시스템</title>
                <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
            </head>

            <body>
                <%-- 쿠키 읽어와서 리스트로 만들어두기 (for문으로 최근 본 책 채워 넣을 용도)--%>
                    <% BookService bookService=new BookService(); List<BookDTO> recentBooks = new ArrayList<>();

                            Cookie[] cookies = request.getCookies();
                            if(cookies != null){
                            for(Cookie cookie : cookies){
                            if("recentBook".equals(cookie.getName())){
                            String value = cookie.getValue();
                            if(value != null && !value.isEmpty()){
                            // 슬래시(/) 대신 언더스코어(_) 사용 권장. 기존 슬래시 쿠키 호환을 위해 둘 다 고려 가능하지만, 명확하게 _로 변경
                            // 만약 값에 %2F 등이 섞여있거나 따옴표가 있어도 안전하게 처리하기 위해 정규식보다는 separator 변경이 확실함
                            String[] bookIds = value.split("_");

                            for(String bookIdStr : bookIds){
                            try {
                            int bookId = Integer.parseInt(bookIdStr.trim());
                            BookDTO book = bookService.selectBookById(bookId);
                            if (book != null){
                            recentBooks.add(book);
                            }
                            } catch (NumberFormatException e) {
                            // 숫자 변환 실패 시(예: "3/1" 처럼 잘못 쪼개지거나 빈 문자열) 무시하고 계속 진행
                            continue;
                            }
                            }
                            }
                            }
                            }
                            }
                            request.setAttribute("recentBooks",recentBooks);

                            %>

                            <header>
                                <div class="container header-content">
                                    <h1>도서 예약 시스템</h1>
                                    <c:if test="${not empty sessionScope.loginUser}">
                                        <div class="user-info">
                                            <span>${sessionScope.loginUser.name}님 환영합니다!</span>
                                            <button onclick="location.href='${pageContext.request.contextPath}/login'"
                                                class="btn-logout">로그아웃</button>
                                        </div>
                                    </c:if>
                                </div>
                            </header>

                            <main class="container">

<%--                                <!-- 관리자 전용 메뉴 -->--%>
<%--                                <c:if test="${sessionScope.loginUser.role eq 'ADMIN'}">--%>
<%--                                    <a href="${pageContext.request.contextPath}/book/insert" class="menu-card admin-card">--%>
<%--                                        <div class="menu-title">도서 추가</div>--%>
<%--                                        <div class="menu-desc">새로운 도서 등록</div>--%>
<%--                                    </a>--%>

<%--                                    <a href="#" class="menu-card admin-card" onclick="alert('구현 예정입니다: 도서 수정')">--%>
<%--                                        <div class="menu-title">도서 수정</div>--%>
<%--                                        <div class="menu-desc">도서 정보 수정 </div>--%>
<%--                                    </a>--%>

<%--                                    <a href="#" class="menu-card admin-card" onclick="alert('구현 예정입니다: 도서 제거')">--%>
<%--                                        <div class="menu-title">도서 제거</div>--%>
<%--                                        <div class="menu-desc">도서 삭제 </div>--%>
<%--                                    </a>--%>
<%--                                </c:if>--%>

<<<<<<< HEAD
                                    <a href="${pageContext.request.contextPath}/book/update?bookId=1" class="menu-card admin-card">
                                        <div class="menu-title">도서 수정</div>
                                        <div class="menu-desc">도서 정보 수정 </div>
                                    </a>

                                    <a href="#" class="menu-card admin-card" onclick="alert('구현 예정입니다: 도서 제거')">
                                        <div class="menu-title">도서 제거</div>
                                        <div class="menu-desc">도서 삭제 </div>
                                    </a>
                                </c:if>

                                <!-- 예약 관련 메뉴 분리 -->
=======
>>>>>>> cf952bffdcbec9348e488674220c7c5d0447b225
                                <c:choose>
                                    <c:when test="${empty sessionScope.loginUser}">
                                        <!-- 로그인 폼 -->
                                        <div class="login-container">
                                            <h2>로그인</h2>
                                            <form action="${pageContext.request.contextPath}/login" method="post"
                                                class="login-form">
                                                <div class="form-group">
                                                    <input type="text" id="userId" name="userId"
                                                        value="${cookie.saveUserId.value}" required
                                                        placeholder="아이디를 입력해주세요.">
                                                    <!-- TODO: 쿠키에 저장된 아이디가 있다면 value에 설정 -->
                                                </div>
                                                <div class="form-group">
                                                    <input type="password" id="userPwd" name="userPwd" required
                                                        placeholder="비밀번호를 입력해주세요.">
                                                </div>
                                                <div class="form-group"
                                                    style="display: flex; flex-direction: row; align-items: center; gap: 8px; margin-bottom: 15px;">
                                                    <label for="saveId"
                                                        style="margin-bottom: 0px; font-size: 0.9rem; color: #555;">아이디
                                                        저장</label>
                                                    <input type="checkbox" id="saveId" name="saveId"
                                                        style="width: auto; margin: 0;">
                                                </div>
                                                <button type="submit" class="btn-login"
                                                    style="background-color: #2c3e50; height: 50px; font-size: 1rem;">로그인</button>
                                                <div
                                                    style="margin-top: 20px; text-align: center; font-size: 0.85rem; color: #666;">
                                                    <a href="#" style="color: #666; text-decoration: none;">아이디 찾기</a>
                                                    <span style="margin: 0 10px; color: #ccc;">|</span>
                                                    <a href="#" style="color: #666; text-decoration: none;">비밀번호 찾기</a>
                                                    <span style="margin: 0 10px; color: #ccc;">|</span>
                                                    <a href="${pageContext.request.contextPath}/regist"
                                                        style="color: #666; text-decoration: none;">회원가입</a>
                                                </div>
                                            </form>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <!-- 메인 메뉴 -->
                                        <div style="text-align: center; margin-bottom: 2rem;">
                                            <h2>메인 메뉴</h2>
                                            <p style="color: var(--text-secondary);">원하시는 작업을 선택해주세요.</p>
                                        </div>

                                        <!-- 오늘의 도서 추천 섹션 -->
                                        <% com.ohgiraffers.service.RecommendationService recService=new
                                            com.ohgiraffers.service.RecommendationService(); com.ohgiraffers.dto.BookDTO
                                            todayBook=recService.getHourlyRecommendedBook(); %>
                                            <div class="featured-section">
                                                <div class="featured-label">Today's Pick</div>
                                                <% if (todayBook !=null) { %>
                                                    <div class="featured-image-container">
                                                        <img src="${pageContext.request.contextPath}<%= todayBook.getImageUrl() %>"
                                                            alt="추천 도서"
                                                            onerror="this.src='https://via.placeholder.com/150x220?text=No+Image'">
                                                    </div>
                                                    <div class="featured-title">
                                                        <%= todayBook.getTitle() %>
                                                    </div>
                                                    <div class="featured-author">
                                                        <%= todayBook.getAuthor() %> 저
                                                    </div>
                                                    <% } else { %>
                                                        <div class="featured-image-container">
                                                            <img src="https://via.placeholder.com/150x220?text=No+Books"
                                                                alt="No Books">
                                                        </div>
                                                        <div class="featured-title">추천 도서를 불러올 수 없습니다.</div>
                                                        <% } %>
                                            </div>

                                            <!-- 최근 본 도서 섹션 (Skeleton) -->
                                            <!-- 최근 본 도서 섹션 (Right Sidebar) -->
                                            <div class="recent-books-list"
                                                style="position: fixed; top: 150px; right: 20px; width: 130px; 
                                                       display: flex; flex-direction: column; gap: 10px; 
                                                       background: white; padding: 15px 10px; border-radius: 10px; 
                                                       box-shadow: 0 4px 6px rgba(0,0,0,0.1); border: 1px solid #eee; z-index: 1000;">
                                                <h3
                                                    style="font-size: 0.9rem; text-align: center; margin-bottom: 5px; color: #555;">
                                                    최근 본 도서</h3>
                                                <c:choose>
                                                    <c:when test="${empty recentBooks}">
                                                        <div
                                                            style="color: #999; font-size: 0.8rem; text-align: center;">
                                                            기록 없음</div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:forEach var="book" items="${recentBooks}">
                                                            <!-- 각 도서 카드 -->
                                                            <a href="${pageContext.request.contextPath}/book/detail?bookId=${book.bookId}"
                                                                style="text-decoration: none; color: inherit; display: flex; flex-direction: column; align-items: center;">
                                                                <img src="${pageContext.request.contextPath}${book.imageUrl}"
                                                                    style="width: 80px; height: 110px; object-fit: cover; border-radius: 4px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
                                                                <div
                                                                    style="font-size: 0.8rem; margin-top: 5px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; width: 100%; text-align: center;">
                                                                    ${book.title}
                                                                </div>
                                                            </a>
                                                        </c:forEach>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>


                                            <div class="menu-grid">
                                                <a href="${pageContext.request.contextPath}/book/list"
                                                    class="menu-card">
                                                    <div class="menu-title">도서 조회</div>
                                                    <div class="menu-desc">도서 목록 확인 및 검색</div>
                                                </a>

                                                <!-- 관리자 전용 메뉴 -->
                                                <c:if test="${sessionScope.loginUser.role eq 'ADMIN'}">
                                                    <a href="${pageContext.request.contextPath}/book/insert" class="menu-card admin-card">
<%--                                                        onclick="alert('구현 예정입니다: 도서 추가')">--%>
                                                        <div class="menu-title">도서 추가</div>
                                                        <div class="menu-desc">새로운 도서 등록</div>
                                                    </a>

                                                    <a href="#" class="menu-card admin-card"
                                                        onclick="alert('구현 예정입니다: 도서 수정')">
                                                        <div class="menu-title">도서 수정</div>
                                                        <div class="menu-desc">도서 정보 수정 </div>
                                                    </a>

                                                    <a href="#" class="menu-card admin-card"
                                                        onclick="alert('구현 예정입니다: 도서 제거')">
                                                        <div class="menu-title">도서 제거</div>
                                                        <div class="menu-desc">도서 삭제 </div>
                                                    </a>
                                                </c:if>

                                                <!-- 예약 관련 메뉴 분리 -->
                                                <c:choose>
                                                    <c:when test="${sessionScope.loginUser.role eq 'ADMIN'}">
                                                        <a href="#" class="menu-card admin-card"
                                                            onclick="alert('구현 예정입니다: 예약 관리 (관리자)')">
                                                            <div class="menu-title">예약 관리</div>
                                                            <div class="menu-desc">전체 예약 현황 관리</div>
                                                        </a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="#" class="menu-card"
                                                            onclick="alert('구현 예정입니다: 도서 예약 (사용자)')">
                                                            <div class="menu-title">도서 예약</div>
                                                            <div class="menu-desc">도서 대출 예약 신청</div>
                                                        </a>
                                                    </c:otherwise>
                                                </c:choose>

                                            </div>
                                    </c:otherwise>
                                </c:choose>

                            </main>

                            <footer>
                                <p>&copy; Team 4. 강성훈, 박찬진, 손창우, 최연후 </p>
                            </footer>
            </body>

            </html>