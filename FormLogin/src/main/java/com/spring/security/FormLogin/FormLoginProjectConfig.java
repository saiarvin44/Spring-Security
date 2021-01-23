package com.spring.security.FormLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class FormLoginProjectConfig extends WebSecurityConfigurerAdapter {
  @Autowired CustomAuthenticationSuccessHandler authenticationSuccessHandler;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.formLogin().successHandler(authenticationSuccessHandler);
    http.authorizeRequests().anyRequest().authenticated();
  }
}
