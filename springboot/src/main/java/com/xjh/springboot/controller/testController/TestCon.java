package com.xjh.springboot.controller.testController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin //类级跨域
@RestController
public class TestCon {
    @CrossOrigin //方法级跨域
    @RequestMapping("/test")
    public String test(){
        return "hello springboot!";
    }
}
