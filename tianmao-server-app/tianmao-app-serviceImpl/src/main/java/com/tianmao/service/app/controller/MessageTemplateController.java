package com.tianmao.service.app.controller;

import com.tianmao.api.app.MessageTemplateClient;
import com.tianmao.service.app.MessageTemplateService;
import com.tianmao.service.app.UserMessageService;
import com.tianmao.service.app.mapper.MessageTemplateMapper;
import com.tianmao.service.dto.user.MessageContentDto;
import com.tianmao.service.model.user.MessageTemplate;
import com.tianmao.service.model.user.UserMessage;
import com.tianmao.service.type.user.MessageTemplateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 消息模板服务
 *
 * @author roach
 * @date 2018/1/6
 */
@RestController
public class MessageTemplateController implements MessageTemplateClient {


    @Autowired
    private MessageTemplateService messageTemplateService;
    @Override
    public String getMessageContent(@RequestBody MessageContentDto messageContentDto, String message) {
        return messageTemplateService.getMessageContent(messageContentDto,message);
    }
}
