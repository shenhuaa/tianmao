package com.tianmao.app.mapper;

import com.tianmao.type.user.MessageTemplateType;
import org.apache.ibatis.annotations.Param;
import com.tianmao.model.user.MessageTemplate;
import com.tianmao.mybatis.BaseMapper;

public interface MessageTemplateMapper extends BaseMapper<MessageTemplate> {
    MessageTemplate getMessageTemplateByMessageType(@Param("messageType") MessageTemplateType messageType);
}
