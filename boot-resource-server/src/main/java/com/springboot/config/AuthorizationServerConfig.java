package com.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	 @Autowired
	   @Qualifier("authenticationManagerBean")
	   private AuthenticationManager authenticationManager;
	   
	   @Bean
	   public JwtAccessTokenConverter tokenEnhancer() {
	      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	      converter.setSigningKey("secret");
	      return converter;
	   }
	   @Bean
	   public JwtTokenStore tokenStore() {
	      return new JwtTokenStore(tokenEnhancer());
	   }
	   @Override
	   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
	      .accessTokenConverter(tokenEnhancer());
	   }
	   @Override
	   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	      security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	   }
	 	
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("javainuse").secret("secret").scopes("read", "write")
        .authorizedGrantTypes("authorization_code").accessTokenValiditySeconds(20000)
        .refreshTokenValiditySeconds(20000);
    }
}