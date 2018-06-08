package com.tianmao.service.app.serviceImpl;

import com.github.pagehelper.PageInfo;
import com.tianmao.service.common.HttpCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tianmao.service.app.mapper.UserMapper;
import com.tianmao.service.model.user.User;
import com.tianmao.service.app.UserService;
import com.tianmao.service.exception.BaseServiceException;

import java.util.List;

@RestController
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectList() {
        return userMapper.selectAllData();
    }

    @Override
    public User getUserByUsername(String username) {
        User user = new User();
        user.setMobile(username);
        return userMapper.selectOne(user);
    }

    @Override
    public User getUserById(Long userId) {
        return null;
    }

    @Override
    public User registerAccountBind(@RequestBody User user) {
        return null;
    }
}
