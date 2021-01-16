package com.spring.security.basics;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
    UserDetails user = User.withUsername("Arvind").password("password").authorities("read").build();
    userDetailsService.createUser(user);
    auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic();
    http.authorizeRequests().anyRequest().authenticated(); // all endpoints are authenticated
    // http.authorizeRequests().anyRequest().permitAll();  // this permits all endpoints without authentication
  }

  /***
   * same thing defined above can be done eloboratively like this

    @Bean
    public UserDetailsService userDetailsService() {
    InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
    UserDetails user = User.withUsername("Arvind").password("password").authorities("read").build();
    userDetailsService.createUser(user);
    return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
    }

   */

}
