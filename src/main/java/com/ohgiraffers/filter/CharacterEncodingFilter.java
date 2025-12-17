package com.ohgiraffers.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = "UTF-8";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

      HttpServletRequest httpRequest = (HttpServletRequest) request;
      String path = httpRequest.getRequestURI();

      request.setCharacterEncoding(encoding);
      response.setCharacterEncoding(encoding);
      if (!path.contains("/resources/")) {
        response.setContentType("text/html; charset=UTF-8");
      }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
