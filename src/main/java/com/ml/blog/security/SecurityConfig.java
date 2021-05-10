package com.ml.blog.security;

import com.ml.blog.qq.QQLoginConfig;
import com.ml.blog.security.jwt.JwtAuthenticationTokenFilter;
import com.ml.blog.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author Mr.ml
 * @date 2020/12/5
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserService userService;
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                // 配置BCrypt加密
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and().csrf().disable()
                //.apply(qqLoginConfig())
                //.and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/index")
//                .failureUrl("/login.html")
//                .successHandler(mySuthenticationSuccessHandler)
//                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .anyRequest().permitAll().and().logout().permitAll();
                //.antMatchers("/login.html", "/login").permitAll()
                //.anyRequest().access("@rbacService.hasPermission(request, authentication)");
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.applyPermitDefaultValues();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /*@Bean
    public SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> qqLoginConfig() {
        return new QQLoginConfig();
    }*/

}
