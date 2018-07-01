package com.tianmao.service.app.controller;

import com.tianmao.api.app.UserMessageClient;
import com.tianmao.service.app.UserMessageService;
import com.tianmao.service.app.mapper.UserMapper;
import com.tianmao.service.app.mapper.UserMessageMapper;
import com.tianmao.service.model.user.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserMessageController implements UserMessageClient {

    @Autowired
    private UserMessageService userMessageService;
    @Override
    public boolean saveUserMessage(@RequestBody UserMessage userMessage) {
        return userMessageService.saveUserMessage(userMessage);
    }

    @Override
    public boolean updateUserMessageByIds(String ids) {
        return userMessageService.updateUserMessageByIds(ids);
    }

    @Override
    public List<UserMessage> getUserMessageListById(Long userId) {
        return userMessageService.getUserMessageListById(userId);
    }
}
