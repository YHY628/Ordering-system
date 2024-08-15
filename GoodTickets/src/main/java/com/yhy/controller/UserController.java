package com.yhy.controller;


import com.yhy.resp.R;
import com.yhy.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/d/user/")
public class UserController {

    @Resource
    private UserService userService;


    @PostMapping("login")
    public R<?> login(String phone,String code) throws Exception {

        String token=userService.login(phone,code);
        return R.SUCCESS("token",token);
    }
    @GetMapping("getUserinfo")
    public R<?> getUserInfo(String token) throws Exception {
        return R.SUCCESS("usermessage",userService.getUser(token));
    }
}
