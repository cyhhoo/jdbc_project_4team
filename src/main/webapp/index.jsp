<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="UTF-8">
            <title>도서 예약 시스템</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
        </head>

        <body>
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

                <c:if test="${not empty requestScope.message}">
                    <script>alert('${requestScope.message}');</script>
                </c:if>

                <c:choose>
                    <c:when test="${empty sessionScope.loginUser}">
                        <!-- 로그인 폼 -->
                        <div class="login-container">
                            <h2>로그인</h2>
                            <form action="${pageContext.request.contextPath}/login" method="post" class="login-form">
                                <div class="form-group">
                                    <label for="userId">아이디</label>
                                    <input type="text" id="userId" name="userId"
                                           value="${cookie.saveUserId.value}"
                                           required placeholder="아이디를 입력하세요">
                                    <!-- TODO: 쿠키에 저장된 아이디가 있다면 value에 설정 -->
                                </div>
                                <div class="form-group" style="flex-direction: row; align-items: center; gap: 5px;">
                                    <input type="checkbox" id="saveId" name="saveId">
                                    <label for="saveId" style="margin-bottom: 0;">아이디 저장</label>
                                </div>
                                <div class="form-group">
                                    <label for="userPwd">비밀번호</label>
                                    <input type="password" id="userPwd" name="userPwd" required
                                        placeholder="비밀번호를 입력하세요">
                                </div>
                                <button type="submit" class="btn-login">로그인</button>
                                <div style="margin-top: 10px; text-align: center;">
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
                                            <img src="https://via.placeholder.com/150x220?text=No+Books" alt="No Books">
                                        </div>
                                        <div class="featured-title">추천 도서를 불러올 수 없습니다.</div>
                                        <% } %>
                            </div>

                            <!-- 최근 본 도서 섹션 (Skeleton) -->
                            <div class="recent-books-section"
                                style="margin-top: 2rem; padding: 1rem; background: #fff; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
                                <h3>최근 본 도서</h3>
                                <!-- TODO: [최근 본 도서 목록 표시 구현] -->
                                <!-- 1) request에서 최근 본 도서 목록(List<BookDTO>)을 가져와서 반복문(c:forEach)으로 출력 -->
                                <c:forEach var="book" items="${recentBook}" varStatus="상태변수">
                                    <p>도서 제목: ${변수명.title}</p>
                                </c:forEach>
                                <!-- 2) 데이터가 없을 경우 "최근 본 도서가 없습니다" 메시지 표시 -->

                                <div class="recent-books-list"
                                    style="display: flex; gap: 1rem; overflow-x: auto; padding: 10px 0;">
                                    <!-- 예시용 더미 데이터 (구현 후 삭제) -->
                                    <div style="color: #999; font-style: italic;">
                                        여기에 최근 본 도서가 표시됩니다.<br>
                                        (상세 페이지 진입 시 쿠키 저장 -> 메인에서 쿠키 읽기 -> DB 조회 -> 표시)
                                    </div>
                                </div>
                            </div>


                            <div class="menu-grid">
                                <a href="${pageContext.request.contextPath}/book/list" class="menu-card">
                                    <div class="menu-title">도서 조회</div>
                                    <div class="menu-desc">도서 목록 확인 및 검색</div>
                                </a>



                                <!-- 관리자 전용 메뉴 -->
                                <c:if test="${sessionScope.loginUser.role eq 'ADMIN'}">
                                    <a href="#" class="menu-card admin-card" onclick="alert('구현 예정입니다: 도서 추가')">
                                        <div class="menu-title">도서 추가</div>
                                        <div class="menu-desc">새로운 도서 등록</div>
                                    </a>

                                    <a href="#" class="menu-card admin-card" onclick="alert('구현 예정입니다: 도서 수정')">
                                        <div class="menu-title">도서 수정</div>
                                        <div class="menu-desc">도서 정보 수정 </div>
                                    </a>

                                    <a href="#" class="menu-card admin-card" onclick="alert('구현 예정입니다: 도서 제거')">
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
                                        <a href="#" class="menu-card" onclick="alert('구현 예정입니다: 도서 예약 (사용자)')">
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