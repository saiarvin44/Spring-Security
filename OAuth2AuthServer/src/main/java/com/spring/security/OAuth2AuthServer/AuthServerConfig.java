package com.spring.security.OAuth2AuthServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
  @Autowired private AuthenticationManager authenticationManagerBean;

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManagerBean);
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

    /***
     * This is for Password grant type
     *
     * clients
     * .inMemory()
     * .withClient("client")
     * .secret("secret")
     * .authorizedGrantTypes("password")
     * .scopes("read");
     ***/

    /***
     * This is for client credentils grant type
     *
     * clients.inMemory()
     * .withClient("client")
     * .secret("secret")
     * .authorizedGrantTypes("client_credentials")
     * .scopes("info");
     ***/

    /***
     *This is for refresh token grant type
     *
    clients
        .inMemory()
        .withClient("client")
        .secret("secret")
        .authorizedGrantTypes("password", "refresh_token")
        .scopes("read");
     ***/

    // Authorisation code grant type

    clients
        .inMemory()
        .withClient("client")
        .secret("secret")
        .authorizedGrantTypes("authorization_code")
        .scopes("read")
        .redirectUris("http://localhost:9090/home");

    /*
    InMemoryClientDetailsService service = new InMemoryClientDetailsService();
    BaseClientDetails cd = new BaseClientDetails();
    cd.setClientId("client");
    cd.setClientSecret("secret");
    cd.setScope(List.of("read"));
    cd.setAuthorizedGrantTypes(List.of("password"));
    Map<String, BaseClientDetails> map = new HashMap<>();
    map.put("client", cd);
    service.setClientDetailsStore(map);
    clients.withClientDetails(service);
    */

  }
}
