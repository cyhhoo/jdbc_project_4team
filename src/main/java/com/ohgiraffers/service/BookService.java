package com.ohgiraffers.service;

import com.ohgiraffers.dao.BookDAO;
import com.ohgiraffers.dto.BookDTO;

import java.sql.Connection;
import java.util.List;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class BookService {

    private final BookDAO bookDAO;

    public BookService() {
        this.bookDAO = new BookDAO();
    }

    public List<BookDTO> selectAllBooks() {
        Connection con = getConnection();
        List<BookDTO> bookList = bookDAO.selectAllBooks(con);
        close(con);
        return bookList;
    }

    public BookDTO selectBookById(int bookId) {
        Connection con = getConnection();
        BookDTO book = bookDAO.selectBookById(con, bookId);
        close(con);
        return book;
    }

    public int insertBook(BookDTO book) {
      Connection con = getConnection();

      int result = bookDAO.insertBook(con, book);

      // 성공 여부에 따라 commit/rollback 지정
      if (result > 0) {
        commit(con);
      }else {
        rollback(con);
      }

      close(con);
      return result;
    }
}
