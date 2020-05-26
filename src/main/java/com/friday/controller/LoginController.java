package com.friday.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Administrator
 */
@Controller
public class LoginController {

    @GetMapping("/login.html")
    public String login(){
        return "login";
    }

    @GetMapping("/403.html")
    public String noPermission(){
        return "403";
    }
}
