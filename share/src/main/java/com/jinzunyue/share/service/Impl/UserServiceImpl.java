package com.jinzunyue.share.service.Impl;

import com.jinzunyue.share.entity.Menu;
import com.jinzunyue.share.entity.Privilege;
import com.jinzunyue.share.entity.Role;
import com.jinzunyue.share.entity.User;
import com.jinzunyue.share.mapper.UserMapper;
import com.jinzunyue.share.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public List<Role> findRolesByUserId(Integer userId) {
        return userMapper.findRolesByUserId(userId);
    }

    public List<Menu> findMenusByRoleId(Integer roleId) {
        return userMapper.findMenusByRoleId(roleId);
    }

    public List<Privilege> findPrivilegesByRoleId(Integer roleId) {
        return userMapper.findPrivilegesByRoleId(roleId);
    }
}
