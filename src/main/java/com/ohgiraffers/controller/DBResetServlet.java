package com.ohgiraffers.controller;

import com.ohgiraffers.common.JDBCTemplate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;
import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.commit;
import static com.ohgiraffers.common.JDBCTemplate.rollback;

@WebServlet("/reset")
public class DBResetServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // 1. Delete all existing users
            String deleteQuery = "DELETE FROM tbl_user";
            pstmt = con.prepareStatement(deleteQuery);
            pstmt.executeUpdate();
            close(pstmt);

            // 2. Insert Admin User (password: admin)
            String insertAdmin = "INSERT INTO tbl_user (user_id, user_pwd, user_name, user_role) VALUES (?, ?, ?, ?)";
            pstmt = con.prepareStatement(insertAdmin);
            pstmt.setString(1, "admin");
            pstmt.setString(2, encoder.encode("admin")); // Hash password
            pstmt.setString(3, "관리자");
            pstmt.setString(4, "ADMIN");
            pstmt.executeUpdate();
            close(pstmt);

            // 3. Insert Normal User (password: user01)
            String insertUser = "INSERT INTO tbl_user (user_id, user_pwd, user_name, user_role) VALUES (?, ?, ?, ?)";
            pstmt = con.prepareStatement(insertUser);
            pstmt.setString(1, "user01");
            pstmt.setString(2, encoder.encode("pass01")); // Hash password
            pstmt.setString(3, "일반유저");
            pstmt.setString(4, "USER");
            pstmt.executeUpdate();

            commit(con);

            out.println("<h1>데이터 초기화 완료</h1>");
            out.println("<p>기존 데이터가 삭제되고 초기 계정이 생성되었습니다.</p>");
            out.println("<ul>");
            out.println("<li>관리자: admin / admin</li>");
            out.println("<li>사용자: user01 / pass01</li>");
            out.println("</ul>");
            out.println("<a href='" + request.getContextPath() + "/index.jsp'>메인으로 이동</a>");

        } catch (SQLException e) {
            rollback(con);
            e.printStackTrace();
            out.println("<h1>초기화 실패</h1>");
            out.println("<p>" + e.getMessage() + "</p>");
        } finally {
            close(pstmt);
            close(con);
        }
    }
}
