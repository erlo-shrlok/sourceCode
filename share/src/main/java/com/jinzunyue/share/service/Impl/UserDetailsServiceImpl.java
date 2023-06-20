package com.jinzunyue.share.service.Impl;

import com.jinzunyue.share.entity.Role;
import com.jinzunyue.share.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 被Spring Security用来加载用户信息
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserServiceImpl userService;

    /**
     * 根据用户名加载用户的详细信息
     *
     * 这段代码的主要作用是加载用户的详细信息，
     * 包括用户名、密码和权限列表。这些信息将被封装在一个UserDetails对象中，
     * 然后被Spring Security用于身份验证和权限控制
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<Role> roles = userService.findRolesByUserId(user.getId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 遍历用户的所有角色，将每个角色转换为一个GrantedAuthority对象，然后添加到权限列表中
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getId() + ":" + role.getValue()));
        }
        // 创建一个UserDetails对象，包含了用户的用户名、密码和权限列表，然后返回这个对象
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
