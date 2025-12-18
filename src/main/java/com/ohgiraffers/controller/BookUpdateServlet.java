package com.ohgiraffers.controller;

import com.ohgiraffers.dto.BookDTO;
import com.ohgiraffers.service.BookService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

    @WebServlet("/book/update")
    public class BookUpdateServlet extends HttpServlet {

        private BookService bookService = new BookService();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            int bookId = Integer.parseInt(req.getParameter("bookId"));

            BookDTO book = bookService.selectBookById(bookId);
            req.setAttribute("book", book);

            req.getRequestDispatcher("/views/book/bookUpdateForm.jsp")
                    .forward(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            req.setCharacterEncoding("UTF-8"); // 필터가 있어도 안전하게
            System.out.println(req.getParameter("bookId"));
            int bookId = Integer.parseInt(req.getParameter("bookId"));
            String title = req.getParameter("title");
            String author = req.getParameter("author");
            int price = Integer.parseInt(req.getParameter("price"));
            String imageUrl = req.getParameter("imageUrl");

            BookDTO dto = new BookDTO();
            dto.setBookId(bookId);
            dto.setTitle(title);
            dto.setAuthor(author);
            dto.setPrice(price);
            dto.setImageUrl(imageUrl);

            int result = bookService.modifyBook(dto);

            if (result > 0) {
                resp.sendRedirect(req.getContextPath() + "/book/detail?bookId=" + bookId);
            } else {
                req.setAttribute("message", "도서 수정 실패");
                req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
            }
        }
    }
