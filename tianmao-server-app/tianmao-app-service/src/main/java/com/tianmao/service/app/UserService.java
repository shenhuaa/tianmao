package com.tianmao.service.app;

import com.tianmao.service.common.BaseService;
import com.tianmao.service.model.user.User;

import java.util.List;

public interface UserService extends BaseService<Long,User> {

    List<User> selectList();
    User getUserByUsername( String username);

    User getUserById(Long userId);

    User registerAccountBind(User user);

    void getException();
}
