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

    public BookDTO deleteBook(int bookId){
      Connection con = getConnection();

      BookDTO book = bookDAO.selectBookById(con, bookId);

      if(book == null) return null;

      int result = bookDAO.deleteBookById(con, bookId);

      if(result > 0) {
        commit(con);
        return book;

      } else{
        rollback(con);
        return null;
      }

    }
}
