package com.tianmao.service.app.mapper;

import com.tianmao.service.model.user.MessageTemplate;
import com.tianmao.service.type.user.MessageTemplateType;
import org.apache.ibatis.annotations.Param;
import com.tianmao.service.mybatis.BaseMapper;

public interface MessageTemplateMapper extends BaseMapper<MessageTemplate> {
    MessageTemplate getMessageTemplateByMessageType(@Param("messageType") MessageTemplateType messageType);
}
