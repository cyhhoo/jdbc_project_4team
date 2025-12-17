package com.ohgiraffers.controller;

import com.ohgiraffers.service.BookService;
import com.ohgiraffers.dto.BookDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/book/detail")
public class BookDetailServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        int bookId = 0;

        try {
            bookId = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/book/list");
            return;
        }

        BookService bookService = new BookService();
        BookDTO book = bookService.selectBookById(bookId);

        if (book != null) {
            request.setAttribute("book", book);
            request.getRequestDispatcher("/views/book/detail.jsp").forward(request, response);
        } else {
            // No book found
            request.setAttribute("message", "해당 도서를 찾을 수 없습니다.");
            request.getRequestDispatcher("/views/book/list.jsp").forward(request, response);
        }
    }
}
