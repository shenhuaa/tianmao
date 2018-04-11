package tianmao.app.mapper;

import org.apache.ibatis.annotations.Param;
import tianmao.model.user.MessageTemplate;
import tianmao.mybatis.BaseMapper;
import tianmao.type.user.MessageTemplateType;

public interface MessageTemplateMapper extends BaseMapper<MessageTemplate> {
    MessageTemplate getMessageTemplateByMessageType(@Param("messageType") MessageTemplateType messageType);
}
