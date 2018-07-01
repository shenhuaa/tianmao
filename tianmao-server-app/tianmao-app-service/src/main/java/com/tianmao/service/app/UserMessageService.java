package com.tianmao.service.app;

import com.tianmao.service.model.user.UserMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserMessageService {
    /**
     * 添加用户系统消息数据
     */
    boolean saveUserMessage(UserMessage userMessage);

    /**
     * 添加用户系统消息数据
     */
    boolean updateUserMessageByIds(@RequestParam("ids") String ids);

    /**
     * 通过用户id获取所有没有读的数据
     */
    List<UserMessage> getUserMessageListById(@RequestParam("userId") Long userId);

}


