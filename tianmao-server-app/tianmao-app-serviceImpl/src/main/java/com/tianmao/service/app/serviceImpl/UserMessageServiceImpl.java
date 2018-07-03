package com.tianmao.service.app.serviceImpl;

import com.tianmao.service.app.mapper.UserMessageMapper;
import com.tianmao.service.app.UserMessageService;
import com.tianmao.service.app.mapper.UserMapper;
import com.tianmao.service.model.user.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class UserMessageServiceImpl implements UserMessageService {

    @Autowired
    private UserMessageMapper userMessageMapper;

    @Autowired
    private UserMapper userMapper;



    /**
     * 添加用户系统消息数据
     * @param userMessage
     */
    @Override
    public boolean saveUserMessage(@RequestBody UserMessage userMessage) {
        if(userMessageMapper.insertSelective(userMessage) < 1 ) {
            Assert.isTrue(false, "添加用户系统消息失败!");
        }
        return true;
    }
    /**
     * 添加用户系统消息数据
     */
    @Override
    public boolean updateUserMessageByIds(String ids) {
        if(userMessageMapper.updateMessageByids(ids) < 1) {
            Assert.isTrue(false, "更新用户系统消息失败!");
        }
        return true;
    }
    /**
     * 通过用户id获取所有没有读的数据
     */
    @Override
    public List<UserMessage> getUserMessageListById(Long userId) {
        return userMessageMapper.getUserMessageById(userId);
    }
}
