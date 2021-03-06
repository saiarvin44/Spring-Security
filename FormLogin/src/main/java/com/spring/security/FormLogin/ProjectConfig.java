package com.spring.security.FormLogin;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// @Configuration  //uncomment this to enable adding a header during authentication failure
public class ProjectConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic(
        c -> {
          c.realmName("OTHER");
          c.authenticationEntryPoint(new CustomEntryPoint());
        });
    http.authorizeRequests().anyRequest().authenticated();
  }
}
