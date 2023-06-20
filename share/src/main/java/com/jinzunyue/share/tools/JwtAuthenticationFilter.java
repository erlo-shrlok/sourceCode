package com.jinzunyue.share.tools;

import com.jinzunyue.share.service.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // 自动注入JwtTokenProvider，用于处理JWT相关的操作
    @Autowired
    private JwtTokenProvider tokenProvider;

    // 自动注入UserDetailsServiceImpl，用于加载用户的身份信息
    @Autowired
    private UserDetailsServiceImpl userService;

//    private final JwtTokenProvider tokenProvider;
//    private final UserDetailsServiceImpl userService;
//
//    @Autowired
//    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider, UserDetailsServiceImpl userService) {
//        this.tokenProvider = tokenProvider;
//        this.userService = userService;
//    }


    /**
     * 这个方法会在每个HTTP请求到达时被调用。
     * 它首先从请求头中获取JWT，然后使用JwtTokenProvider来验证这个JWT，
     * 如果验证成功，那么就从JWT中获取用户的用户名，
     * 然后使用这个用户名来加载用户的身份信息，
     * 最后，将这个身份信息设置到SecurityContext中,这样后续的处理器就可以从SecurityContext中获取到这个用户的身份信息。
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            // 如果JWT存在并且有效
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                // 从JWT中获取用户名
                String username = tokenProvider.getUsernameFromJWT(jwt);

                // 加载用户的身份信息
                UserDetails userDetails = userService.loadUserByUsername(username);
                // 创建一个身份验证令牌，其中包含了用户的身份信息和权限
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                // 设置身份验证令牌的详细信息
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // 将身份验证令牌设置到SecurityContext中
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            // 如果在处理过程中发生异常，记录错误信息
            logger.error("Could not set user authentication in security context", ex);
        }
        // 继续执行后续的过滤器
        filterChain.doFilter(request, response);
    }

    /**
     * 从请求头中获取JWT。
     * JWT通常在"Authorization"请求头中，以"Bearer "为前缀。
     * @param request
     * @return
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        // 获取"Authorization"请求头的值
        String bearerToken = request.getHeader("Authorization");
        // 如果请求头的值存在并且以"Bearer "为前缀
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            // 去掉前缀，返回真正的JWT
            return bearerToken.substring(7, bearerToken.length());
        }
        // 如果请求头的值不存在或者格式不正确，返回null
        return null;
    }
}

