package com.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class EmployeeSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/user/getEmployeesList")
            .hasAnyRole("ADMIN").anyRequest().authenticated().and().formLogin()
            .permitAll().and().logout().permitAll();

        http.csrf().disable();
    }
   
    @Override
    @Autowired
    public void configure(AuthenticationManagerBuilder authenticationMgr) throws Exception {
        authenticationMgr.inMemoryAuthentication().withUser("admin").password("admin")
            .authorities("ROLE_ADMIN");
    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
       return super.authenticationManagerBean();
    }
}
