package com.jinzunyue.share.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuMapperTest {

    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void selectMenusByRoleIds(){
        List<Integer> ids = new ArrayList<>();
        ids.add(6);
        System.out.println(menuMapper.selectMenusByRoleIds(ids));
    }
}
