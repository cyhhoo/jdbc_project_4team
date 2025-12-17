<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="UTF-8">
            <title>도서 목록</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
            <style>
                .pagination {
                    display: flex;
                    justify-content: center;
                    list-style: none;
                    gap: 5px;
                    margin-top: 2rem;
                }

                .pagination a {
                    padding: 8px 12px;
                    text-decoration: none;
                    color: var(--primary-color);
                    border: 1px solid var(--border-color);
                    border-radius: 4px;
                }

                .pagination a.active {
                    background-color: var(--primary-color);
                    color: white;
                    border-color: var(--primary-color);
                }

                .search-container {
                    margin-top: 2rem;
                    text-align: center;
                }

                .search-input {
                    padding: 0.5rem;
                    border: 1px solid var(--border-color);
                    border-radius: 4px;
                    width: 300px;
                }
            </style>
        </head>

        <body>
            <header>
                <div class="container header-content">
                    <h1><a href="${pageContext.request.contextPath}/" style="text-decoration: none; color: inherit;">도서
                            관리 시스템</a></h1>
                    <c:if test="${sessionScope.loginUser.role eq 'ADMIN'}">
                        <a href="#" class="btn btn-primary">도서 등록</a>
                    </c:if>
                </div>
            </header>

            <main class="container">
                <div class="card">
                    <div
                        style="margin-bottom: 1.5rem; display: flex; justify-content: space-between; align-items: center;">
                        <h2>모든 도서 조회하기</h2>
                        <span style="color: var(--text-secondary); font-size: 0.875rem;">총 ${totalCount}권</span>
                    </div>

                    <table>
                        <thead>
                            <tr>
                                <th style="width: 10%;">번호</th>
                                <%-- 요구사항: 모든 도서 조회 시 책 번호, 이름, 저자만 표시 --%>
                                    <%-- <th style="width: 15%;">이미지</th> --%>
                                        <th style="width: 50%;">제목</th>
                                        <th style="width: 30%;">저자</th>
                                        <%-- <th style="width: 15%;">가격</th> --%>
                                            <c:if test="${sessionScope.loginUser.role eq 'ADMIN'}">
                                                <th style="width: 10%;">관리</th>
                                            </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty bookList}">
                                    <c:forEach var="book" items="${bookList}">
                                        <tr>
                                            <td>${book.bookId}</td>
                                            <%-- <td>
                                                <div
                                                    style="width: 60px; height: 80px; background-color: #eee; border-radius: 4px; overflow: hidden; display: flex; align-items: center; justify-content: center;">
                                                    <c:if test="${not empty book.imageUrl}">
                                                        <img src="${pageContext.request.contextPath}${book.imageUrl}"
                                                            alt="책 표지"
                                                            style="width: 100%; height: 100%; object-fit: cover;">
                                                    </c:if>
                                                    <c:if test="${empty book.imageUrl}">
                                                        <span style="font-size: 0.7rem; color: #999;">No Image</span>
                                                    </c:if>
                                                </div>
                                                </td>
                                                --%>
                                                <td style="font-weight: 500;">
                                                    <a href="${pageContext.request.contextPath}/book/detail?id=${book.bookId}"
                                                        style="text-decoration: none; color: inherit; display: block;">
                                                        ${book.title}
                                                    </a>
                                                </td>
                                                <td>${book.author}</td>
                                                <%-- <td class="price-tag">${book.price}원</td> --%>
                                                    <c:if test="${sessionScope.loginUser.role eq 'ADMIN'}">
                                                        <td>
                                                            <div class="actions">
                                                                <button class="btn btn-sm btn-edit">수정</button>
                                                                <button class="btn btn-sm btn-delete">삭제</button>
                                                            </div>
                                                        </td>
                                                    </c:if>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="4" class="empty-state">
                                            검색 결과가 없습니다.
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>

                    <!-- 페이지네이션 -->
                    <div class="pagination">
                        <c:if test="${currentPage > 1}">
                            <a href="?page=${currentPage - 1}&keyword=${keyword}">이전</a>
                        </c:if>

                        <c:forEach begin="1" end="${totalPage}" var="metaPage">
                            <a href="?page=${metaPage}&keyword=${keyword}"
                                class="${currentPage == metaPage ? 'active' : ''}">${metaPage}</a>
                        </c:forEach>

                        <c:if test="${currentPage < totalPage}">
                            <a href="?page=${currentPage + 1}&keyword=${keyword}">다음</a>
                        </c:if>
                    </div>

                    <!-- 검색 (하단) -->
                    <div class="search-container">
                        <form action="${pageContext.request.contextPath}/book/list" method="get">
                            <input type="text" name="keyword" class="search-input" placeholder="책 제목, 저자 또는 초성 검색"
                                value="${keyword}">
                            <button type="submit" class="btn btn-primary">검색</button>
                        </form>
                    </div>

                </div>
            </main>

            <footer>
                <p>&copy; Team 4. 강성훈, 박찬진, 손창우, 최연후 </p>
            </footer>
        </body>

        </html>