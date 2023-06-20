package com.jinzunyue.share.tools;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthenticationEntryPoint是Spring Security用来处理身份验证异常的接口。
 * 当用户试图访问需要身份验证的资源但又没有提供有效的JWT时，
 * 就会抛出一个身份验证异常，
 * 然后调用AuthenticationEntryPoint的commence方法来处理这个异常
 *
 * 在这个类中，我们重写了commence方法，
 * 当用户试图访问需要身份验证的资源但又没有提供有效的JWT时，这个方法就会被调用。
 * 在这个方法中，我们调用response.sendError方法来返回一个HTTP 401 Unauthorized错误，
 * 并且设置错误信息为"当前用户无权限访问此资源"。
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "当前用户无权限访问此资源");
    }
}
