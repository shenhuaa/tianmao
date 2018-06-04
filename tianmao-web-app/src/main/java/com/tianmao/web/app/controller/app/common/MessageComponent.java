package com.tianmao.web.app.controller.app.common;

import com.tianmao.service.app.MessageTemplateService;
import com.tianmao.type.user.MessageTemplateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tianmao.dto.user.MessageContentDto;
import com.tianmao.web.app.util.JPushUtil;
import java.util.HashMap;
import java.util.Map;
@Component
public class MessageComponent {

    @Autowired
    private MessageTemplateService messageTemplateService;

    /**
     * 极光推送
     *
     * @return
     */
    public boolean senJPushUtil(MessageTemplateType messageType, Long userId, Long outId, String... content) {
        //消息推送
        MessageContentDto messageContentDto = new MessageContentDto(messageType, userId, content);
        String message = "";
        String messageContent = messageTemplateService.getMessageContent(messageContentDto, message);
        Map<String, String> extras = new HashMap<>();
        //加入用户消息总数
        extras.put("id", outId + "");
        extras.put("type", String.valueOf(messageType.ordinal()));
        JPushUtil.sendPush(userId, messageContent, extras);
        return true;
    }

}