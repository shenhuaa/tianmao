package com.tianmao.config;

import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

/**
 * 微信支付配置文件
 *
 * @author roach
 * @date 2017/12/21
 */
@ConfigurationProperties(prefix = "weixin.pay.chat")
@Configuration
@Data
public class WxChatpayConfig implements WXPayConfig {

    private static final Logger logger = LoggerFactory.getLogger(WxChatpayConfig.class);

    /**
     * 开发平台号appid
     */
    private String appid;

    private String secret;

    /**
     * key为商户平台设置的密钥key, 在商户平台-》设置API密钥
     */
    private String key;

    /**
     * 商户id
     */
    private String mchid;

    /**
     * 聊天室支微信支付回调url
     */
    private String chatNotifyUrl;

    /**
     * 商城商品购买微信支付回调url
     */
    private String mallNotifyUrl;

    /**
     * 商城钱包充值微信支付回调url
     */
    private String walletNotifyUrl;



    /**
     * 支付类型
     */
    private String tradeType = "APP";

    private String chatTradeType = "APP";

    private WXPayConstants.SignType signType = WXPayConstants.SignType.HMACSHA256;

    @Override
    public String getAppID() {
        return this.getAppid();
    }

    @Override
    public String getMchID() {
        return this.getMchid();
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public InputStream getCertStream() {
        return null;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }


}