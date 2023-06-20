package com.jinzunyue.share.service;

import com.jinzunyue.share.entity.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getMenusByRoles(List<String> roles);
}
