package com.xrest.emanagement.core;

import com.xrest.emanagement.core.JWT.Authentication;
import com.xrest.emanagement.core.JWT.Authorization;
import com.xrest.emanagement.repository.UserRepository;
import com.xrest.emanagement.serviceimpl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration

public class SecurityCore extends WebSecurityConfigurerAdapter {
    private final UserRepository userRepository;

    private final UserServiceImpl userService;

    public SecurityCore(UserRepository userRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().addFilter(new Authentication(authenticationManager())).addFilter(new Authorization(authenticationManager(),userRepository)).authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/v1/user/register").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public PasswordEncoder getEncoder() {
        return  new BCryptPasswordEncoder();
    }

    DaoAuthenticationProvider daoAuthenticationProvider () {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(getEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }
}
