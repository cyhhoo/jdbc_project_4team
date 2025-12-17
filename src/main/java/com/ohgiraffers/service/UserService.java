package com.ohgiraffers.service;

import com.ohgiraffers.dao.UserDAO;
import com.ohgiraffers.dto.UserDTO;

import java.sql.Connection;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;
import static com.ohgiraffers.common.JDBCTemplate.close;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }

    public UserDTO login(String userId, String userPwd) {
        Connection con = getConnection();

        // 1. Get user by ID
        UserDTO user = userDAO.selectUserById(con, userId);

        // 2. Check password if user exists
        if (user != null) {
            org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
            if (!encoder.matches(userPwd, user.getPwd())) {
                user = null; // Password mismatch
            }
        }

        close(con);
        return user;
    }

    public int registUser(UserDTO newUser) {
        Connection con = getConnection();
        int result = userDAO.registUser(con, newUser);
        if (result > 0) {
            try {
                con.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                con.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        close(con);
        return result;
    }
}
