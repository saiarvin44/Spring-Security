package com.spring.security.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestValidationFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    String reqId = httpRequest.getHeader("Request-Id");
    if (reqId == null || reqId.isEmpty()) {
      httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }
    chain.doFilter(request, response);
  }
}
