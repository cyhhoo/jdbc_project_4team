package com.ohgiraffers.controller;

import com.ohgiraffers.service.BookService;
import com.ohgiraffers.dto.BookDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/book/list")
public class BookListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BookService bookService = new BookService();

        // 1. 모든 데이터 조회 (In-Memory Filtering/Paging for Blueprint simplicity)
        List<BookDTO> allBooks = bookService.selectAllBooks();

        // 2. 검색 필터링
        String keyword = request.getParameter("keyword");
        List<BookDTO> filteredList = new ArrayList<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            for (BookDTO book : allBooks) {
                // 제목 또는 저자에 대해 검색 (초성 포함)
                if (com.ohgiraffers.common.HangulUtils.matchString(book.getTitle(), keyword) ||
                        com.ohgiraffers.common.HangulUtils.matchString(book.getAuthor(), keyword)) {
                    filteredList.add(book);
                }
            }
        } else {
            filteredList = allBooks;
        }

        // 3. 페이지네이션
        int page = 1;
        try {
            String pageStr = request.getParameter("page");
            if (pageStr != null)
                page = Integer.parseInt(pageStr);
        } catch (NumberFormatException e) {
            page = 1;
        }

        int limit = 30; // 페이지당 30권
        int totalCount = filteredList.size();
        int totalPage = (int) Math.ceil((double) totalCount / limit);

        if (page < 1)
            page = 1;
        if (page > totalPage && totalPage > 0)
            page = totalPage;

        int start = (page - 1) * limit;
        int end = Math.min(start + limit, totalCount);

        List<BookDTO> pagedList = new ArrayList<>();
        if (start < totalCount) {
            pagedList = filteredList.subList(start, end);
        }

        request.setAttribute("bookList", pagedList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("keyword", keyword);
        request.setAttribute("totalCount", totalCount);

        request.getRequestDispatcher("/views/book/list.jsp").forward(request, response);
    }
}
