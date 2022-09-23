package com.shravan.springboot.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class LoginControllerTest {

    @Autowired
    LoginController loginController;

    @Test
    void login() {
        String expected="login";
        String actual= loginController.login();
        assertEquals(expected,actual);
    }

    @Test
    void showAccessDenied() {
        String expected="access-denied";
        String actual= loginController.showAccessDenied();
        assertEquals(expected,actual);
    }
}





















