package com.kakao.bank.test.mapsearch.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.bank.test.mapsearch.security.*;
import com.kakao.bank.test.mapsearch.security.ajax.AjaxAuthenticationFilter;
import com.kakao.bank.test.mapsearch.security.ajax.AjaxAuthenticationProvider;
import com.kakao.bank.test.mapsearch.security.jwt.JwtAuthenticationFilter;
import com.kakao.bank.test.mapsearch.security.jwt.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationProvider jwtProvider;

    @Autowired
    private AjaxAuthenticationProvider ajaxProvider;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityHandler securityHandler;

    private static final String LOGIN_ENTRY_POINT = "/login";
    private static final String LOGIN_FORM_ENTRY_POINT = "/index.html";
    private static final String TOKEN_ENTRY_POINT = "/token";
    private static final String ERROR_ENTRY_POINT = "/error";
    private static final String ROOT_ENTRY_POINT = "/**";

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/webjars/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(ajaxProvider)
                .authenticationProvider(jwtProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(ajaxAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(TOKEN_ENTRY_POINT).permitAll()
                .antMatchers(LOGIN_ENTRY_POINT).permitAll()
                .antMatchers(ERROR_ENTRY_POINT).permitAll()
                .antMatchers(LOGIN_FORM_ENTRY_POINT).permitAll()
                .antMatchers(ROOT_ENTRY_POINT).authenticated()
                .and()
                .logout()
        ;
    }

    @Bean
    public AntPathRequestMatcher antPathRequestMatcher() {
        return new AntPathRequestMatcher(LOGIN_ENTRY_POINT, HttpMethod.POST.name());
    }

    @Bean
    public AjaxAuthenticationFilter ajaxAuthenticationFilter() throws Exception {
        AjaxAuthenticationFilter filter = new AjaxAuthenticationFilter(antPathRequestMatcher(), objectMapper);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(securityHandler);
        filter.setAuthenticationFailureHandler(securityHandler);
        return filter;
    }

    @Bean
    public SkipPathRequestMatcher skipPathRequestMatcher() {
        return new SkipPathRequestMatcher(Arrays.asList(
                LOGIN_ENTRY_POINT,
                TOKEN_ENTRY_POINT,
                ERROR_ENTRY_POINT,
                LOGIN_FORM_ENTRY_POINT,
                "/webjars/**",
                "/js/**",
                "/css/**"));
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(skipPathRequestMatcher());
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationFailureHandler(securityHandler);
        return filter;
    }
}
