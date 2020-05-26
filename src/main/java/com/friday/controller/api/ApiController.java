package com.friday.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 公共api接口，用于跳转页面
 */
@Controller
@RequestMapping("${api-url}")
public class ApiController {

    @GetMapping("/get/view")
    public ModelAndView getView(ModelAndView modelAndView,String viewName){
        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
