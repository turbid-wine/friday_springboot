package com.friday;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class FridayApplicationTests {

    @Autowired
    private BCryptPasswordEncoder bpe;
    @Test
    void contextLoads() {
        System.out.println(bpe.encode("admin"));
    }

}
