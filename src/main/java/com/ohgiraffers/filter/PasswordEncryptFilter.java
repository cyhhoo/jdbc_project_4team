package com.ohgiraffers.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/regist")
public class PasswordEncryptFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // Only apply wrapper for registration to encrypt the password before saving
        // For login, we need the raw password to verify against the hash, so this
        // filter should NOT be applied to login.
        // The annotation @WebFilter("/regist") ensures it only applies to registration.

        PasswordRequestWrapper wrapper = new PasswordRequestWrapper(httpRequest);
        chain.doFilter(wrapper, response);
    }

    @Override
    public void destroy() {
    }
}
