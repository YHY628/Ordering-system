package com.yhy.controller;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.yhy.exception.InfoException;
import com.yhy.model.User;
import com.yhy.resp.R;
import com.yhy.service.UserService;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class UserController {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public R<?> login(User user, HttpServletRequest req) throws Exception {
        String phone=user.getPhone();
        System.out.println(phone);
        String password = user.getPassword();
        User data=userService.findUserByphone(phone);

        if (ObjUtil.isNull(data)) {
            throw new LoginException("手机号未注册");
        }

        if (!StrUtil.equals(SecureUtil.sha256(password), data.getPassword())) {
            throw new LoginException("密码不正确");
        }

        HttpSession session = req.getSession();
        session.setAttribute("userId", data.getId());
        String username=data.getUsername();

        return R.SUCCESS("user login", username);
    }

    public R<String> reguser(User user) throws Exception {
        // 补全信息
        user.setPassword(SecureUtil.sha256(user.getPassword()));

        // 调用业务流程
        userService.addUser(user);

        // 返回 servlet
        return R.SUCCESS("add user", "OK");
    }
}
