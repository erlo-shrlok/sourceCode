package com.jinzunyue.share.service.Impl;

import com.jinzunyue.share.entity.Menu;
import com.jinzunyue.share.mapper.MenuMapper;
import com.jinzunyue.share.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 根据角色列表查menu
     * @param roles
     * @return
     */
    @Override
    public List<Menu> getMenusByRoles(List<String> roles) {
        List<Integer> roleIds = new ArrayList<>();
        for (String role : roles) {
            // 将权限字符串分割成角色ID和角色名
            String[] parts = role.split(":");
            if (parts.length == 2) {
                // 将角色ID转换为Int并添加到列表中
                roleIds.add(Integer.parseInt(parts[0]));
            }
        }
        return menuMapper.selectMenusByRoleIds(roleIds);
    }
}
