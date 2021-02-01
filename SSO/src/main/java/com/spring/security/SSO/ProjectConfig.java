package com.spring.security.SSO;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

  private ClientRegistration clientRegistration() {
    return CommonOAuth2Provider.GITHUB
        .getBuilder("github")
        .clientId("clientId")
        .clientSecret("clientSecret")
        .build();
  }

  @Bean
  public ClientRegistrationRepository clientRepository() {
    ClientRegistration c = clientRegistration();
    return new InMemoryClientRegistrationRepository(c);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.oauth2Login();

    http.authorizeRequests().anyRequest().authenticated();
  }
}
