package tianmao.service;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tianmao.dto.user.MessageContentDto;

/**
 * 消息模板服务
 *
 * @author roach
 * @date 2018/1/6
 */
@FeignClient(name = "${server.post.name}")
public interface MessageTemplateService {

    @RequestMapping(value = "/message-template-type/content")
    String getMessageContent(MessageContentDto messageContentDto, @RequestParam("message") String message);

}
