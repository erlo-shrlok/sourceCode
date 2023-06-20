package com.jinzunyue.share.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * JwtSecurityConfigurer类是一个自定义的安全配置类，
 * 它的主要作用是将JwtAuthenticationFilter添加到Spring Security的过滤器链中。
 * 这样，每次请求都会经过这个过滤器，从而实现JWT的验证和身份信息的加载。
 *
 * 在这个类中，我们首先创建了一个JwtAuthenticationFilter实例，然后将它添加到了过滤器链中。
 * 我们使用addFilterBefore方法来确保这个过滤器在UsernamePasswordAuthenticationFilter之前运行，
 * 因为我们需要在用户名和密码验证之前验证JWT。
 *
 * 另外，我们还设置了一个JwtAuthenticationEntryPoint实例作为身份验证入口点。
 * 这个入口点会在用户试图访问需要身份验证的资源但又没有提供有效的JWT时被调用。
 * 你可以根据你的需求来实现这个类，比如返回一个包含错误信息的HTTP响应
 */
public class JwtSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtAuthenticationFilter customFilter;

    public JwtSecurityConfigurer(JwtAuthenticationFilter customFilter) {
        this.customFilter = customFilter;
    }

    // 重写configure方法，用于配置HttpSecurity对象
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 配置HttpSecurity对象
        // 配置异常处理
        http.exceptionHandling()
                // 设置自定义的身份验证入口点，当需要身份验证时，如果没有提供有效的JWT，将返回一个提示”当前用户无权限访问此资源“的HTTP错误响应信息
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                .and()
                // 这样，当一个HTTP请求到达时，首先会经过JwtAuthenticationFilter进行JWT的验证，然后再经过UsernamePasswordAuthenticationFilter进行用户名和密码的验证
                .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
