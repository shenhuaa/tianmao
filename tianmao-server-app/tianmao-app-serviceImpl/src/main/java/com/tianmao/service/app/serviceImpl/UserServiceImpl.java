package com.tianmao.service.app.serviceImpl;

import com.tianmao.service.JmsConstant;
import com.tianmao.service.RabbitmqService;
import com.tianmao.service.app.UserService;
import com.tianmao.service.app.mapper.UserMapper;
import com.tianmao.service.common.serviceImpl.BaseServiceImpl;
import com.tianmao.service.exception.BaseServiceException;
import com.tianmao.service.model.user.User;
import com.tianmao.utils.HttpCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<Long,User>  implements UserService {

    @Autowired
    private RabbitmqService rabbitmqService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectList() {
        Long userId = 2L;
        rabbitmqService.send(JmsConstant.APP_TOPIC_EXCHANGE, JmsConstant.SYSTEM_PUSH_ROUTING_KEY, userId);
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

    @Override
    public void getException() {
        throw new BaseServiceException(HttpCode.ILLEGAL_REQUEST,"测试抛异常了");
    }
}
