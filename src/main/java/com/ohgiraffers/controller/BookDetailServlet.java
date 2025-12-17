package com.ohgiraffers.controller;

import com.ohgiraffers.dao.BookDAO;
import com.ohgiraffers.dto.BookDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;
import static com.ohgiraffers.common.JDBCTemplate.close;

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

        BookDAO bookDAO = new BookDAO();
        Connection con = getConnection();

        BookDTO book = bookDAO.selectBookById(con, bookId);

        close(con);

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
