package com.ohgiraffers.controller;

import com.ohgiraffers.dto.UserDTO;
import com.ohgiraffers.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/regist")
public class RegistServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/regist.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("userId");
        String userPwd = request.getParameter("userPwd"); // This will be encrypted by the Filter
        String userName = request.getParameter("userName");

        UserDTO newUser = new UserDTO();
        newUser.setId(userId);
        newUser.setPwd(userPwd);
        newUser.setName(userName);

        UserService userService = new UserService();
        int result = userService.registUser(newUser);

        if (result > 0) {
            request.setAttribute("message", "회원가입이 완료되었습니다. 로그인해주세요.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "회원가입에 실패하였습니다.");
            request.getRequestDispatcher("/regist.jsp").forward(request, response);
        }
    }
}
