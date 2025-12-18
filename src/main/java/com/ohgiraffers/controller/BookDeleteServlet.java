package com.ohgiraffers.controller;

import com.ohgiraffers.dto.BookDTO;
import com.ohgiraffers.service.BookService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/book/delete")
public class BookDeleteServlet extends HttpServlet {

  private final BookService bookService = new BookService();

  /**
   * 삭제 확인 페이지를 보여주는 역할
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/views/book/delete.jsp").forward(request, response);
  }


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String bookIdStr = request.getParameter("bookId");
    int bookId;

    try {
      bookId = Integer.parseInt(bookIdStr);
    } catch (NumberFormatException e) {
      request.setAttribute("error", "유효하지 않은 도서 ID입니다.");
      request.getRequestDispatcher("/views/common/error.jsp").forward(request, response);
      return;
    }

    // 삭제할 도서 정보 조회 (BookService에 상세 조회 메소드가 필요합니다)
    BookDTO book = bookService.deleteBook(bookId);

    if (book != null) {
      request.setAttribute("book", book);
    } else {
      request.setAttribute("error", "삭제할 도서를 찾을 수 없습니다.");
    }

    request.getRequestDispatcher("/views/book/delete-result.jsp").forward(request, response);
  }
}

