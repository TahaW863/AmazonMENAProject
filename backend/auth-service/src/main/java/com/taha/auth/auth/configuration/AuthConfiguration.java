package com.taha.auth.auth.configuration;


import com.taha.auth.auth.filters.CustomAuthenticateFilter;
import com.taha.auth.auth.filters.CustomAuthorizeFilter;
import com.taha.auth.auth.interfaces.UserService;
import com.taha.auth.auth.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.taha.auth.auth.enums.Roles.ADMIN;
import static com.taha.auth.auth.enums.Roles.USER;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class AuthConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;
    private final JwtService jwtService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticateFilter customAuthenticateFilter = new CustomAuthenticateFilter(authenticationManagerBean(), userService, bCryptPasswordEncoder, jwtService);
        customAuthenticateFilter.setFilterProcessesUrl("/api/v1/auth/login");

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/api/v1/auth/**").permitAll()
                .anyRequest().authenticated();

        http.addFilter(customAuthenticateFilter);
        /*http.authorizeRequests()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/discovery/**").permitAll()
                .antMatchers("/api/v1/schemas/**").hasAnyAuthority(ADMIN.toString())
                .antMatchers("/api/v1/query/node/**").hasAnyAuthority(ADMIN.toString(), USER.toString())
                .antMatchers("/api/v1/query/master/**").hasAnyAuthority(ADMIN.toString())
                .anyRequest().authenticated();

        http.addFilter(customAuthenticateFilter);
        http.addFilterBefore(new CustomAuthorizeFilter(), UsernamePasswordAuthenticationFilter.class);*/
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
