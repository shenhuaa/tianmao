package com.tianmao.service.app;

import com.tianmao.service.model.user.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {

    List<User> selectList();

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    User getUserByUsername(@RequestParam("username") String username);

    User getUserById(@RequestParam("userId")Long userId);

    User registerAccountBind(User user);

    void getException();
}
