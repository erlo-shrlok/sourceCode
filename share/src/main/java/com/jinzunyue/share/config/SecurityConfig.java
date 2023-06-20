package com.jinzunyue.share.config;

import com.jinzunyue.share.service.Impl.UserDetailsServiceImpl;
import com.jinzunyue.share.tools.JwtAuthenticationFilter;
import com.jinzunyue.share.tools.JwtSecurityConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 禁用了CSRF保护，因为我们不需要它。
 * 使得所有的请求都需要认证。
 * 允许所有的用户访问"/login"路径。
 * 使用了JWT来验证用户身份。
 * 忽略了一些路径，这些路径主要是Swagger UI的路径，我们不希望这些路径被保护起来。
 * 配置了一个PasswordEncoder，我们使用的是BCryptPasswordEncoder。
 * 配置了一个AuthenticationManager，这个Manager会被Spring Security用来处理认证。
 *
 *
 * 这段代码的主要作用是配置Spring Security的安全策略，
 * 包括HTTP安全策略、Web安全策略、身份验证提供者和密码编码器等
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    // 重写WebSecurityConfigurerAdapter类的configure方法，用于配置HttpSecurity
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 禁用CSRF保护
                .csrf().disable()
                // 配置session管理策略为无状态，即不创建session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 配置权限要求
                .authorizeRequests()
                // 对"/login"路径的请求不需要身份验证
                .antMatchers("/login").permitAll()
                .antMatchers("/areaCode/add").permitAll()
                // 对其他所有路径的请求都需要身份验证
                .anyRequest().authenticated()
                .and()
                // 应用自定义的JWT安全配置
                .apply(new JwtSecurityConfigurer(jwtAuthenticationFilter));
    }

    // 重写WebSecurityConfigurerAdapter类的configure方法，用于配置WebSecurity
    @Override
    public void configure(WebSecurity web) {
        // 对以下路径的请求忽略安全检查
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        // 设置密码编码器为NoOpPasswordEncoder，即不进行密码编码
//        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     *  定义一个Bean，用于提供AuthenticationManager的实例
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}


