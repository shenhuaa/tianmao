package tianmao.web.app.controller.app.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tianmao.service.UserService;
import tianmao.web.app.util.JPushUtil;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageComponent {

    @Autowired
    private MessageTemplateService messageTemplateService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedReceiveService redReceiveService;


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
        User user = new User();
        user.setId(userId);
        UserData userData = userService.selectUserDataById(user);
        //统计数量
        int commentRead = userData.getCommentRead();
        int inboxRead = userData.getInboxRead();
        int isReadCount = userData.getIsReadCount();
        int unreceivedCount = redReceiveService.unreceivedCount(userId);
        //加入用户消息总数
        extras.put("myMessageCount", commentRead + inboxRead + isReadCount + unreceivedCount + "");
        extras.put("id", outId + "");
        extras.put("type", String.valueOf(messageType.ordinal()));
        JPushUtil.sendPush(userId, messageContent, extras);
        return true;
    }

}