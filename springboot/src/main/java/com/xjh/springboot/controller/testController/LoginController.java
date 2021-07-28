package com.xjh.springboot.controller.testController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @CrossOrigin //方法级跨域
    @RequestMapping("/login")
    public String test(){
        return "ok";
    }
}