package com.tianmao.app.api.common;

import com.tianmao.api.app.MessageTemplateClient;
import com.tianmao.app.util.JPushUtil;
import com.tianmao.service.dto.user.MessageContentDto;
import com.tianmao.service.type.user.MessageTemplateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageComponent {

    @Autowired
    private MessageTemplateClient messageTemplateClient;

    /**
     * 极光推送
     *
     * @return
     */
    public boolean senJPushUtil(MessageTemplateType messageType, Long userId, Long outId, String... content) {
        //消息推送
        MessageContentDto messageContentDto = new MessageContentDto(messageType, userId, content);
        String message = "";
        String messageContent = messageTemplateClient.getMessageContent(messageContentDto, message);
        Map<String, String> extras = new HashMap<>();
        //加入用户消息总数
        extras.put("id", outId + "");
        extras.put("type", String.valueOf(messageType.ordinal()));
        JPushUtil.sendPush(userId, messageContent, extras);
        return true;
    }

}