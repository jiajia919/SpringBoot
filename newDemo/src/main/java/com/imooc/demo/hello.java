package com.imooc.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class hello {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
    	System.out.println("hello");
        return "hello";
    }
}
