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
      // Load queries from XML using a more robust path
      String resourcePath = "com/ohgiraffers/mapper/book-query.xml";
      prop.loadFromXML(BookDAO.class.getClassLoader().getResourceAsStream(resourcePath));
    } catch (IOException e) {
      // If loading fails, throw a runtime exception to stop the application immediately
      throw new RuntimeException("Failed to load book-query.xml", e);
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

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                return -1;
            }
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int updateBook(Connection con, BookDTO bookDTO) {
        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("updateBook");

        try {
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, bookDTO.getTitle());
            pstmt.setString(2, bookDTO.getAuthor());
            pstmt.setInt(3, bookDTO.getPrice());
            pstmt.setString(4, bookDTO.getImageUrl());
            pstmt.setInt(5, bookDTO.getBookId());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }

    public int deleteBookById(Connection con, int bookId){
      int result = 0;

      PreparedStatement pstmt = null;
      String sql = prop.getProperty("deleteBookById");

      try {
        pstmt = con.prepareStatement(sql);

        pstmt.setInt(1, bookId);

        result = pstmt.executeUpdate();
      } catch (SQLException e){
        throw new RuntimeException(e);
      }finally {
        close(pstmt);
      }
      return result;
    }

}
