package com.spring.security.Managing_Users;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class ProjectConfig {

  @Bean
  public UserDetailsService userDetailsService(DataSource dataSource) {

    /*   // This is for normal inmemory authentication
    UserDetails u = new DummyUser("arvind", "password", "read");
    List<UserDetails> users = Arrays.asList(u);
    return new InMemoryUserDetailsService(users);
    */

    return new JdbcUserDetailsManager(dataSource); // for jdbc authentication
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
