package com.tianmao.service.app;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tianmao.service.model.user.User;

import java.util.List;

@FeignClient(name = "${server.app.name}")
public interface UserService {

    @RequestMapping(value = "/user/selectList")
    List<User> selectList();

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/user/username")
    User getUserByUsername(@RequestParam("username") String username);

    @RequestMapping(value = "/user/getUserById")
    User getUserById(@RequestParam("userId")Long userId);

    @RequestMapping(value = "/user/register/bind")
    User registerAccountBind(User user);
}
