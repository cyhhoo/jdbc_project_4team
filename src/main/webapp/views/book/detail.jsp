<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html lang="ko">

        <head>
            <meta charset="UTF-8">
            <title>도서 상세 정보</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
            <style>
                .detail-container {
                    max-width: 800px;
                    margin: 2rem auto;
                    background: white;
                    padding: 2rem;
                    border-radius: 8px;
                    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                }

                .book-detail-grid {
                    display: grid;
                    grid-template-columns: 250px 1fr;
                    gap: 2rem;
                    margin-bottom: 2rem;
                }

                .book-image {
                    width: 100%;
                    border-radius: 8px;
                    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                    background-color: #f3f4f6;
                    aspect-ratio: 3/4;
                    display: flex;
                    align-items: center;
                    justify-content: center;
                    color: #9ca3af;
                }

                .book-image img {
                    width: 100%;
                    height: 100%;
                    object-fit: cover;
                    border-radius: 8px;
                }

                .book-info h2 {
                    font-size: 2rem;
                    margin-bottom: 1rem;
                    color: var(--text-primary);
                }

                .info-row {
                    margin-bottom: 0.8rem;
                    font-size: 1.1rem;
                }

                .label {
                    font-weight: 600;
                    color: var(--text-secondary);
                    margin-right: 0.5rem;
                }

                .price {
                    font-size: 1.5rem;
                    font-weight: 700;
                    color: var(--primary-color);
                    margin-top: 1rem;
                }

                .btn-group {
                    display: flex;
                    gap: 1rem;
                    justify-content: center;
                    margin-top: 2rem;
                }

                @media (max-width: 768px) {
                    .book-detail-grid {
                        grid-template-columns: 1fr;
                    }

                    .book-image {
                        max-width: 250px;
                        margin: 0 auto;
                    }
                }
            </style>
        </head>

        <body>
            <header>
                <div class="container header-content">
                    <h1><a href="${pageContext.request.contextPath}/" style="text-decoration: none; color: inherit;">도서
                            예약 시스템</a></h1>
                    <c:if test="${not empty sessionScope.loginUser}">
                        <div class="user-info">
                            <span>${sessionScope.loginUser.name}님</span>
                        </div>
                    </c:if>
                </div>
            </header>

            <main class="container">
                <div class="detail-container">
                    <div class="book-detail-grid">
                        <div class="book-image">
                            <c:choose>
                                <c:when test="${not empty book.imageUrl}">
                                    <img src="${pageContext.request.contextPath}${book.imageUrl}" alt="${book.title}"
                                        onerror="this.parentElement.innerHTML='이미지 없음'">
                                </c:when>
                                <c:otherwise>
                                    이미지 없음
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="book-info">
                            <h2>${book.title}</h2>
                            <div class="info-row">
                                <span class="label">저자</span> ${book.author}
                            </div>
                            <div class="info-row">
                                <span class="label">도서 번호</span> ${book.bookId}
                            </div>
                            <div class="price">
                                ${book.price}원
                            </div>
                        </div>
                    </div>

                    <div class="btn-group">
                        <a href="${pageContext.request.contextPath}/book/list" class="btn"
                            style="background-color: #e5e7eb; color: var(--text-primary);">목록으로</a>

                        <c:if test="${sessionScope.loginUser.role eq 'ADMIN'}">
                            <button class="btn btn-primary" onclick="alert('구현 예정: 수정')">수정</button>
                            <button class="btn btn-delete" onclick="alert('구현 예정: 삭제')">삭제</button>
                        </c:if>

                        <c:if test="${sessionScope.loginUser.role ne 'ADMIN'}">
                            <button class="btn btn-primary" onclick="alert('구현 예정: 대출 예약')">예약하기</button>
                        </c:if>
                    </div>
                </div>
            </main>

            <footer>
                <p>&copy; Team 4. 강성훈, 박찬진, 손창우, 최연후</p>
            </footer>
        </body>

        </html>