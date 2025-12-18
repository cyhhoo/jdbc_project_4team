package com.ohgiraffers.controller;

import com.ohgiraffers.dto.BookDTO;
import com.ohgiraffers.service.BookService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/book/insert")
public class BookInsertServlet extends HttpServlet {

  // 1. 등록 페이지로 이동 (GET 방식)
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    request.getRequestDispatcher("/views/book/insertForm.jsp").forward(request, response);
  }

  // 2. 실제 데이터 등록 처리 (POST 방식)
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 인코딩 설정 (한글 깨짐 방지)
    request.setCharacterEncoding("UTF-8");

    // 폼에서 전달된 파라미터 추출
    String title = request.getParameter("title");
    String author = request.getParameter("author");
    int price = Integer.parseInt(request.getParameter("price"));
    String imageUrl = request.getParameter("imageUrl");

    // DTO 객체 생성 및 데이터 담기
    BookDTO newBook = new BookDTO();
    newBook.setTitle(title);
    newBook.setAuthor(author);
    newBook.setPrice(price);
    newBook.setImageUrl(imageUrl);

    // 서비스 계층 호출
    BookService bookService = new BookService();
    int result = bookService.insertBook(newBook);

    // 결과 처리
    if (result > 0) {
      // 등록 성공 시 목록 페이지로 리다이렉트
      request.getSession().setAttribute("successMessage", "도서가 정상적으로 등록되었습니다.");
      response.sendRedirect(request.getContextPath() + "/book/list");
    } else if (result == -1) {
      // ⭐️ 중복 도서 발생 시 처리
      request.setAttribute("message", "이미 등록된 동일한 도서명과 저자가 존재합니다.");
      // 입력했던 정보를 다시 폼에 채워주기 위해 forward 사용
      request.getRequestDispatcher("/views/book/insertForm.jsp").forward(request, response);
    } else {
      // 등록 실패 시 에러 페이지 또는 메시지와 함께 이전 페이지로 이동
      request.setAttribute("message", "도서 등록에 실패하였습니다.");
      request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
    }
  }
}