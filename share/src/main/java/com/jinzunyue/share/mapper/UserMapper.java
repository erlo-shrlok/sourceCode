package com.jinzunyue.share.mapper;

import com.jinzunyue.share.entity.Menu;
import com.jinzunyue.share.entity.Privilege;
import com.jinzunyue.share.entity.Role;
import com.jinzunyue.share.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findByUsername(String username);
    List<Role> findRolesByUserId(Integer userId);
    List<Menu> findMenusByRoleId(Integer roleId);
    List<Privilege> findPrivilegesByRoleId(Integer roleId);
}
