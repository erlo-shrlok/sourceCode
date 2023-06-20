package com.jinzunyue.share.service;

import com.jinzunyue.share.entity.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuServiceTest {

    @Autowired
    private MenuService menuService;

    @Test
    public void getMenusByRoles(){
        List<String> ids = new ArrayList<>();
        ids.add("6:admin");
        menuService.getMenusByRoles(ids);
    }
}
