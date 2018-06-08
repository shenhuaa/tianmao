package com.tianmao.service.web.app.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 极光推送配置文件
 */
@Configuration
@ConfigurationProperties(prefix = "jpush")
@Data
public class JPushConfigurer {

    private String appkey;

    private String secret;

    /**
     *  设置ios平台环境 True 表示推送生产环境，False 表示要推送开发环境 默认是开发
     */
    private boolean apns = false;

}
