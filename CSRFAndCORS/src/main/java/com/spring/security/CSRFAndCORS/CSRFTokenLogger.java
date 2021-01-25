package com.spring.security.CSRFAndCORS;

import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Logger;

public class CSRFTokenLogger implements Filter {

  private Logger logger = Logger.getLogger(CSRFTokenLogger.class.getName());

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    Object o = request.getAttribute("_csrf");
    CsrfToken token = (CsrfToken) o;
    logger.info("CSRF Token " + token.getToken());
    chain.doFilter(request, response);
  }
}
