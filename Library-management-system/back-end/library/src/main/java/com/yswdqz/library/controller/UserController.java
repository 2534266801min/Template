package com.yswdqz.library.controller;

import com.yswdqz.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class UserController {
    @PostMapping("/adminstrator")
    public Result loginByAdmin(Map<String,String> map){
        map.get()
    }
}
