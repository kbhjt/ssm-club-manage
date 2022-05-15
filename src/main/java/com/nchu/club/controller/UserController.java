package com.nchu.club.controller;

import com.nchu.club.domain.User;
import com.nchu.club.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public String login(String username,String password,String utype) {
        User user = userService.login(username,password,Integer.valueOf(utype));
        if (user != null) {
            return "登录成功";
        }
        return "账号或密码错误";
    }
}
