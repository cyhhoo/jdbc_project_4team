package com.ohgiraffers.dao;

import com.ohgiraffers.dto.UserDTO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;

public class UserDAO {

    private Properties prop = new Properties();

    public UserDAO() {
        try {
            prop.loadFromXML(UserDAO.class.getClassLoader()
                    .getResourceAsStream("com/ohgiraffers/mapper/user-query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UserDTO login(Connection con, String userId, String userPwd) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        UserDTO loginUser = null;

        String query = prop.getProperty("login");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, userId);
            pstmt.setString(2, userPwd);

            rset = pstmt.executeQuery();

            if (rset.next()) {
                loginUser = new UserDTO();
                loginUser.setNo(rset.getInt("user_no"));
                loginUser.setId(rset.getString("user_id"));
                loginUser.setPwd(rset.getString("user_pwd"));
                loginUser.setName(rset.getString("user_name"));
                loginUser.setRole(rset.getString("user_role"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }

        return loginUser;
    }

    public int registUser(Connection con, UserDTO newUser) {
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("registUser");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, newUser.getId());
            pstmt.setString(2, newUser.getPwd());
            pstmt.setString(3, newUser.getName());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }

        return result;
    }

    public UserDTO selectUserById(Connection con, String userId) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        UserDTO user = null;
        String query = prop.getProperty("selectUserById");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, userId);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                user = new UserDTO();
                user.setNo(rset.getInt("user_no"));
                user.setId(rset.getString("user_id"));
                user.setPwd(rset.getString("user_pwd"));
                user.setName(rset.getString("user_name"));
                user.setRole(rset.getString("user_role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }

        return user;
    }
}
