package com.jinzunyue.share.service;

import com.jinzunyue.share.entity.Menu;
import com.jinzunyue.share.entity.Privilege;
import com.jinzunyue.share.entity.Role;
import com.jinzunyue.share.entity.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    List<Role> findRolesByUserId(Integer userId);

    List<Menu> findMenusByRoleId(Integer roleId);

    List<Privilege> findPrivilegesByRoleId(Integer roleId);
}
