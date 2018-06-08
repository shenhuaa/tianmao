package com.tianmao.service.app.mapper;

import com.tianmao.service.model.user.User;
import com.tianmao.service.mybatis.BaseMapper;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> selectAllData();
}
