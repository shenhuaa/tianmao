package com.tianmao.service.app.controller;

import com.tianmao.api.app.UserClient;
import com.tianmao.service.app.UserService;
import com.tianmao.service.common.serviceImpl.BaseServiceImpl;
import com.tianmao.service.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController  implements UserClient  {

    @Autowired
    private UserService userService;


    @Override
    public List<User> selectList() {
       // return userService.selectAll(new User());
        return userService.selectList();
    }

    @Override
    public User getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    @Override
    public User getUserById(Long userId) {
        return userService.getUserById(userId);
    }

    @Override
    public User registerAccountBind(@RequestBody User user) {
        return userService.registerAccountBind(user);
    }

    @Override
    public void getException() {
        userService.getException();
    }
}
