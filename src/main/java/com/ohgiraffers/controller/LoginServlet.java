package com.ohgiraffers.controller;

import com.ohgiraffers.service.UserService;
import com.ohgiraffers.dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userId = request.getParameter("userId");
        String userPwd = request.getParameter("userPwd");

        // 1. 아이디 저장 체크박스 값 가져오기
        String saveId = request.getParameter("saveId");

        UserService userService = new UserService();
        UserDTO loginUser = userService.login(userId, userPwd);

        if (loginUser != null) {
            // TODO: [아이디 저장 기능 구현]
            // 1) 로그인 성공 시, saveId가 "on"이면 쿠키에 userId 저장 (유효기간 설정 필수)
            if ("on".equals(saveId)) {
              Cookie loginCookie = new Cookie("saveUserId",userId);
              loginCookie.setPath("/");
              loginCookie.setMaxAge(60 * 60 * 24 * 7);
              response.addCookie(loginCookie);
            }
            // 2) saveId가 null(체크 해제)이면 쿠키 삭제
            else {
              Cookie loginCookie = new Cookie("saveUserId","");
              loginCookie.setPath("/");
              loginCookie.setMaxAge(0);
              response.addCookie(loginCookie);
            }

            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            request.setAttribute("message", "로그인 실패: 아이디 또는 비밀번호를 확인해주세요.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Logout logic
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
