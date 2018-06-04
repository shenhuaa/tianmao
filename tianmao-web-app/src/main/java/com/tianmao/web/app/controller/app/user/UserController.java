package com.tianmao.web.app.controller.app.user;

import com.github.pagehelper.PageInfo;
import com.tianmao.common.HttpCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tianmao.model.user.User;
import com.tianmao.service.app.UserService;
import com.tianmao.web.app.util.Rest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public Rest userList() {
        Rest.Builder rest = Rest.newBuilder();
        PageInfo<User> userPage =  userService.selectList();
        rest.put("list",userPage.getList());
        return rest.code(HttpCode.OK).build();
    }

    @RequestMapping("/common")
    public Rest commonList() {
        Rest.Builder rest = Rest.newBuilder();
        PageInfo<User> userPage =  userService.selectList();
        rest.put("list",userPage.getList());
        return rest.code(HttpCode.OK).build();
    }



}
