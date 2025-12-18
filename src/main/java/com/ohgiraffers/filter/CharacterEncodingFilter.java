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

//@WebFilter("/*") // encoding = "UTF-8" 대신 아래 항목으로 작성할 때 이거 때문에 필터가 두번 등록 되면서 에러가 발생했었음
public class CharacterEncodingFilter implements Filter {

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        encoding = "UTF-8";
        encoding = filterConfig.getInitParameter("encoding-type");
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
