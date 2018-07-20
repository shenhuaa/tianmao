package com.tianmao.controller.app.weixin.config;

import cn.wlxsh.pay.weixin.common.WeixinPayConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 商城微信支付配置文件
 *
 * @author roach
 * @date 2017/12/21
 */
@ConfigurationProperties(prefix = "weixin.pay.mall")
@Configuration
@Data
@EqualsAndHashCode(callSuper = true)
public class MallWxpayConfig extends WeixinPayConfig {

    private String body;

    private String tradeType = "APP";
}