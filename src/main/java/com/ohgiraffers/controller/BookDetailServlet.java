package com.ohgiraffers.controller;

import com.ohgiraffers.service.BookService;
import com.ohgiraffers.dto.BookDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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

        // TODO: [최근 본 도서 기능 구현]
        // 1) 현재 bookId를 포함하여 쿠키 생성 또는 갱신
        String newBookId = String.valueOf(bookId);

        // 2) 기존 쿠키가 있다면 값을 읽어와서 새 ID 추가 (중복 제거, 최대 개수 제한 고려)
        // 이미 존재하는 쿠키 중 recentBook 쿠키 있는지 확인
        Cookie[] cookies = request.getCookies();
        String bookList = "";

        if (cookies != null){
          for (Cookie cookie : cookies){
            if(cookie.getName().equals("recentBook")){
              bookList = cookie.getValue();
              break;
            }
          }
        }

        if (bookList.isEmpty()){
          // 비어있었으면 현재 본 책 아이디 bookList
          bookList = newBookId;
        }
        else {
          String[] items = bookList.split("/");
          StringBuilder sb = new StringBuilder();

          // 쿠키에 내용이 들어있었으면 newBookId가 기존에 들어있었는지 확인해서, 중복이였으면 해당 id는 뒤에 안붙임, 5개 까지만
          sb.append(newBookId);
          int count = 1;
          for(String item : items){
            if(!item.equals(newBookId) && count < 5){
              sb.append("/").append(item);
              count++;
            }
          }
          bookList = sb.toString();
        }
      // 3) 쿠키를 response에 추가
          Cookie bookCookie = new Cookie("recentBook",bookList);
          bookCookie.setPath("/");
          bookCookie.setMaxAge(60*60*24*7);
          response.addCookie(bookCookie);

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
