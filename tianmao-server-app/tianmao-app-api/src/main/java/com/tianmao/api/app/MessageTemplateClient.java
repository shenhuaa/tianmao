package com.tianmao.api.app;

import com.tianmao.service.dto.user.MessageContentDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 消息模板服务
 *
 * @author roach
 * @date 2018/1/6
 */
@FeignClient(name = "${server.app.name}")
public interface MessageTemplateClient {

    @RequestMapping(value = "/message-template-type/content")
    String getMessageContent(MessageContentDto messageContentDto, @RequestParam("message") String message);

}
