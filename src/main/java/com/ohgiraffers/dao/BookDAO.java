package com.ohgiraffers.dao;

import com.ohgiraffers.dto.BookDTO;

import static com.ohgiraffers.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BookDAO {

  private Properties prop = new Properties();

  public BookDAO() {
    try {
      // Load queries from XML
      // Note: Since this is a web app, we might need to load via ClassLoader
      prop.loadFromXML(BookDAO.class.getClassLoader()
          .getResourceAsStream("com/ohgiraffers/mapper/book-query.xml"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<BookDTO> selectAllBooks(Connection con) {
    PreparedStatement pstmt = null;
    ResultSet rset = null;
    List<BookDTO> bookList = null;

    String query = prop.getProperty("selectAllBooks");

    try {
      pstmt = con.prepareStatement(query);
      rset = pstmt.executeQuery();

      bookList = new ArrayList<>();

      while (rset.next()) {
        BookDTO book = new BookDTO();
        book.setBookId(rset.getInt("book_id"));
        book.setTitle(rset.getString("title"));
        book.setAuthor(rset.getString("author"));
        book.setPrice(rset.getInt("price"));
        book.setImageUrl(rset.getString("image_url"));

        bookList.add(book);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(rset);
      close(pstmt);
    }

    return bookList;
  }

  public BookDTO selectBookById(Connection con, int bookId) {
    PreparedStatement pstmt = null;
    ResultSet rset = null;
    BookDTO book = null;

    String query = prop.getProperty("selectBookById");

    try {
      pstmt = con.prepareStatement(query);
      pstmt.setInt(1, bookId);
      rset = pstmt.executeQuery();

      if (rset.next()) {
        book = new BookDTO();
        book.setBookId(rset.getInt("book_id"));
        book.setTitle(rset.getString("title"));
        book.setAuthor(rset.getString("author"));
        book.setPrice(rset.getInt("price"));
        book.setImageUrl(rset.getString("image_url"));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      close(rset);
      close(pstmt);
    }

    return book;
  }

  public int insertBook(Connection con, BookDTO book) {
    int result = 0;

    PreparedStatement pstmt = null;

    String query = prop.getProperty("insertBook");

    try {
      pstmt = con.prepareStatement(query);

      pstmt.setString(1, book.getTitle());
      pstmt.setString(2, book.getAuthor());
      pstmt.setInt(3, book.getPrice());
      pstmt.setString(4, book.getImageUrl());

      result = pstmt.executeUpdate();

    }catch (SQLException e){
      // MySQL/MariaDB에서 중복 키(Duplicate entry) 에러 코드는 1062입니다.
      if (e.getErrorCode() == 1062) {
        return -1; // 중복 발생 시 약속된 기호인 -1 반환
      }
      throw new RuntimeException(e);
    }finally {
      close(pstmt);

    }
    return result;
  }

}
