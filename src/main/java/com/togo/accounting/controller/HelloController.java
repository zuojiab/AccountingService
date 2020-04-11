package com.togo.accounting.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @create 2020-04-11 23:38
 **/
@RestController
public class HelloController {
    /**
     * @GetMapping("v1.0/greeting/{name}")
     * 注解:PathVariable localhost:8080/v1.0/greeting/coming
     * @GetMapping("v1.0/greeting")
     * 注解:RequestParam localhost:8080/v1.0/greeting?name=coming
     */


    @GetMapping("v1.0/greeting")
    public String sayHello(@RequestParam("name") String name){
        return String.format("Hello,%s",name);
    }
}
