package com.tianmao.service.app;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tianmao.service.dto.user.MessageContentDto;

/**
 * 消息模板服务
 *
 * @author roach
 * @date 2018/1/6
 */
public interface MessageTemplateService {

    String getMessageContent(MessageContentDto messageContentDto, @RequestParam("message") String message);

}
