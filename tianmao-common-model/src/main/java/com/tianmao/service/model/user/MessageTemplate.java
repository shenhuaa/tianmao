package com.tianmao.service.model.user;

import com.tianmao.service.type.user.MessageTemplateType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 消息模板
 *
 * @author roach
 * @date 2017/11/10
 */
@Data
@Entity
@Table(name = "system_template_message")
public class MessageTemplate implements Serializable {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;


    /**
     * app图片路径
     */
    private String title;

    /**
     * pc图片路径
     */
    private String content;

    /**
     * 描述
     */
    private MessageTemplateType messageType;


}
