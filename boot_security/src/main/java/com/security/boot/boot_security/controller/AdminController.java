package com.security.boot.boot_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassNameAdminController
 * @Description
 * @Author
 * @Date2020/7/24 9:11
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {


    @GetMapping("/demo")
    public String demo(){
        return "security & boot";
    }

}
