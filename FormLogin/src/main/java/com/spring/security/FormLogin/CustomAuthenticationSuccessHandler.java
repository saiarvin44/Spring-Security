package com.spring.security.FormLogin;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    Optional<? extends GrantedAuthority> auth =
        authorities.stream().filter(a -> a.getAuthority().equals("read")).findFirst();
    if (auth.isPresent()) response.sendRedirect("/home");
    else response.sendRedirect("/error");
  }
}
