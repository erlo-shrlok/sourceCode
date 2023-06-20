package com.jinzunyue.share.controller;

import com.jinzunyue.share.entity.Menu;
import com.jinzunyue.share.entity.User;
import com.jinzunyue.share.service.MenuService;
import com.jinzunyue.share.tools.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HomeController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private MenuService menuService;

    @GetMapping("/hi")
    public String home(){
        return "home";
    }

    /**
     *
     * 这段代码的主要作用是接收一个包含用户名和密码的请求，
     * 然后验证这个用户名和密码，如果验证成功，
     * 就生成一个JWT并返回。这个JWT可以用于后续的请求身份验证，
     * 从而避免了每次请求都需要发送用户名和密码
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        // 使用AuthenticationManager进行身份验证。这个身份验证的过程包括：
        // 1. 从请求体中获取用户的用户名和密码
        // 2. 使用这个用户名和密码创建一个UsernamePasswordAuthenticationToken对象
        // 3. 将这个UsernamePasswordAuthenticationToken对象传递给AuthenticationManager的authenticate方法
        // 如果身份验证成功，authenticate方法会返回一个包含了用户身份信息的Authentication对象
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        // 使用JwtTokenProvider生成一个JWT。这个JWT包含了用户的身份信息，可以用于后续的请求身份验证
        return jwtTokenProvider.generateToken(authentication);
    }

    /**
     * 展示当前用户的角色
     * @return
     */
    @GetMapping("/user/auth")
    public Collection<String> getAuth(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    @GetMapping("/user/menu")
    public List<Menu> getMenu(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        //获取用户的所有权限
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // 根据权限查询用户的所有菜单
        return menuService.getMenusByRoles(roles);
    }

    @GetMapping("/user/menu2")
    public List<Menu> getMenu2(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        //获取用户的所有权限
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // 根据权限查询用户的所有菜单
        List<Menu> privilegeList = menuService.getMenusByRoles(roles);


        return null;
    }
}
