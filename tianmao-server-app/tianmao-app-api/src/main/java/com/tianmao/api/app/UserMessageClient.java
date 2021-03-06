package com.tianmao.api.app;

import com.tianmao.service.model.user.UserMessage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${server.app.name}")
public interface UserMessageClient {



    /**
     * 添加用户系统消息数据
     *
     * @param userMessage
     */
    @RequestMapping(value = "/user/message/saveUserMessage")
    boolean saveUserMessage(UserMessage userMessage);

    /**
     * 添加用户系统消息数据
     */
    @RequestMapping(value = "/user/message/updateUserMessageByIds")
    boolean updateUserMessageByIds(@RequestParam("ids") String ids);

    /**
     * 通过用户id获取所有没有读的数据
     */
    @RequestMapping(value = "/user/message/getUserMessageListById")
    List<UserMessage> getUserMessageListById(@RequestParam("userId") Long userId);

}


