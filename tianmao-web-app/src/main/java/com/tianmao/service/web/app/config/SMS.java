package com.tianmao.service.web.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * 短信实体类（文档接口：https://www.juhe.cn/docs/api/id/54）
 *
 * @author roach
 * @date 2017/11/27
 */
@Data
@ConfigurationProperties(prefix = "sms")
@Configuration
public class SMS implements Serializable {

    private static final long serialVersionUID = 3427824076765377574L;

    /**
     * 接收短信的手机号码
     */
    private String mobile;

    /**
     * 验证码
     */
    private int code;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 爱士多企业id
     */
    private String companyId;

    /**
     * 爱士多登陆账号
     */
    private String username;

    /**
     * 爱士多登陆密码
     */
    private String password;

    /**
     * 爱士多模板内容
     */
    private String content;

}
