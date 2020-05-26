package com.friday.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("friday")
public class IndexController {

    @GetMapping("/index")
    public ModelAndView indexView(){
        return new ModelAndView("index");
    }
}
