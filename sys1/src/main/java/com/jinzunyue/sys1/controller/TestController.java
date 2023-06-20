package com.jinzunyue.sys1.controller;

import com.jinzunyue.share.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
public class TestController {

    @GetMapping("/h")
    public String hi(){
        User u = new User();
        u.setName("诸葛孔明");
        System.out.println(u);
        return "hi";
    }
}
