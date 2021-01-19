package com.spring.security.FormLogin;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class ProjectConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic(
        c -> {
          c.realmName("OTHER");
        });
    http.authorizeRequests().anyRequest().authenticated();
  }
}
