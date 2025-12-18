package com.ohgiraffers.service;

import com.ohgiraffers.dao.BookDAO;
import com.ohgiraffers.dto.BookDTO;

import java.sql.Connection;
import java.util.List;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;
import static com.ohgiraffers.common.JDBCTemplate.close;

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

    public int modifyBook(BookDTO modifyBook) {

        Connection con = getConnection();

        BookDAO bookdao = new BookDAO();

        int result = bookdao.updateBook(con, modifyBook);
        try {
            if (result > 0) con.commit();

            else con.rollback();
        } catch (Exception e) {

        }
        return result;
    }
}
