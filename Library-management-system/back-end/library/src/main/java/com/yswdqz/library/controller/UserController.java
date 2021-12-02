package com.yswdqz.library.controller;

import com.yswdqz.library.mapper.UserMapper;
import com.yswdqz.library.model.Administrator;
import com.yswdqz.library.model.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @PostMapping("/adminstrator")
    public String loginByAdmin(@RequestBody Map<String,String> map){
        String username = map.get("accountNumber");
        String password = map.get("password");
        Administrator administrator = userMapper.loginByAdmin(username, password);

        if(administrator != null){
            return "success";
        }
        return "false";
    }
    @PostMapping("/reader")
    public String loginByReader(@RequestBody Map<String,String> map){
        String username = map.get("accountNumber");
        String password = map.get("password");
        Reader reader = userMapper.loginByReader(username, password);

        if(reader != null){
            return "success";
        }
        return "false";
    }
}
