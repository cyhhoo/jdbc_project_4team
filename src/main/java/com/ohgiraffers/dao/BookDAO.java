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

    public int deleteBookById(Connection con, int bookId){
      int result = 0;

      PreparedStatement pstmt = null;

      try {
        String sql = prop.getProperty("deleteBookById");

        pstmt = con.prepareStatement(sql);

        pstmt.setInt(1, bookId);

        result = pstmt.executeUpdate();
      } catch (Exception e){
        throw new RuntimeException(e);
      }finally {
        close(pstmt);
      }
      return result;
    }

}
