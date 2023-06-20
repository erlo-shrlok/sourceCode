package com.jinzunyue.share;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class ShareApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void pwdEncoder(){
        // 创建一个BCryptPasswordEncoder对象
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 明文密码
        String rawPassword = "111";
        // 加密密码
        String encodedPassword = passwordEncoder.encode(rawPassword);
        // 打印加密后的密码
        System.out.println(encodedPassword);
    }

}
