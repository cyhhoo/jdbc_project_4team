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
        UserDTO user = userDAO.login(con, userId, userPwd);
        close(con);
        return user;
    }
}
