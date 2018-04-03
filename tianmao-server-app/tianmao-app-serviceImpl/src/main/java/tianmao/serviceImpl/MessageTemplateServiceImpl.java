package tianmao.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tianmao.dto.user.MessageContentDto;
import tianmao.mapper.MessageTemplateMapper;
import tianmao.model.user.MessageTemplate;
import tianmao.service.MessageTemplateService;
import tianmao.type.user.MessageTemplateType;

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
public class MessageTemplateServiceImpl implements MessageTemplateService {

    @Autowired
    private MessageTemplateMapper messageTemplateMapper;

    @Autowired
    private UserMessageService userMessageService;

    @Override
    public String getMessageContent(@RequestBody MessageContentDto messageContentDto, String message) {
        Assert.notNull(messageContentDto, "消息模板对象不能为空");
        Assert.notNull(messageContentDto.getMessageType(), "消息模板类型不能为空");
        Assert.notNull(messageContentDto.getContent().length == 0, "消息内容不能为空");
        List<String> newContent = new ArrayList<>();
        for (String content : messageContentDto.getContent()) {
            if (content.length() > 20) {
                newContent.add(content.substring(0, 20) + "...");
            } else {
                newContent.add(content);
            }
        }
        MessageTemplate messageTemplate = messageTemplateMapper.getMessageTemplateByMessageType(messageContentDto.getMessageType());
        String oldContent = messageTemplate.getContent();
        String content = "";
        if(messageContentDto.getMessageType().equals(MessageTemplateType.USER_WITHDRAW_MONEY)){
            content = MessageFormat.format(oldContent,newContent.toArray())+message;
        }else{
            content = MessageFormat.format(oldContent, newContent.toArray());
        }

        //如果不是回复评论就保存系统消息
        if (messageContentDto.getMessageType() != MessageTemplateType.POST_RECEIVE_COMMENT && messageContentDto.getMessageType() != MessageTemplateType.TALK_RECEIVE_COMMENT ) {
            //调用抽取方法
            this.saveUserMessage(messageContentDto.getUserId(), messageTemplate.getTitle(), content);
        }
        return content;
    }

    /**
     * 创建用户对象系统消息对象
     * @param userId
     * @param title
     * @param content
     * @return
     */
    private boolean saveUserMessage(Long userId, String title, String content) {
        UserMessage userMessage = new UserMessage();
        userMessage.setTitle(title);
        userMessage.setContent(content);
        userMessage.setUserId(userId);
        userMessage.setAddTime(new Date());
        return userMessageService.saveUserMessage(userMessage);
    }

}
