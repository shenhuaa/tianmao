package com.tianmao.service.web.app.util;

import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.NettyHttpClient;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jiguang.common.ClientConfig;
import com.tianmao.service.web.app.config.JPushConfigurer;
import io.netty.handler.codec.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * 极光推送工具类
 *
 * @author roach
 * @date 2107/12/28
 */
@Component
public final class JPushUtil {

    private static final Logger logger = LoggerFactory.getLogger(JPushUtil.class);

    @Autowired
    private JPushConfigurer configurer;

    private static JPushConfigurer jpushConfigurer;

    private JPushUtil() {

    }

    @PostConstruct
    public void init() {
        jpushConfigurer = this.configurer;
    }


    /**
     * 异步接口发送请求
     *
     * @param userId  用户id
     * @param content 内容
     */
    public static void sendPush(Long userId, String content) {
        sendPush(userId, content, null);
    }

    /**
     * 异步接口发送请求
     *
     * @param userId  用户id
     * @param content 内容
     * @param extras  参数
     */
    public static void sendPush(Long userId, String content, Map<String, String> extras) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        String host = (String) clientConfig.get(ClientConfig.PUSH_HOST_NAME);
        String basicAuthorization = ServiceHelper.getBasicAuthorization(jpushConfigurer.getAppkey(), jpushConfigurer.getSecret());
        final NettyHttpClient client = new NettyHttpClient(basicAuthorization, null, clientConfig);
        try {
            URI uri = new URI(host + clientConfig.get(ClientConfig.PUSH_PATH));
            PushPayload payload = buildPushObjectAllAliasAlert(String.valueOf(userId), content, extras);
            client.sendRequest(HttpMethod.POST, payload.toString(), uri, new NettyHttpClient.BaseCallback() {
                @Override
                public void onSucceed(ResponseWrapper responseWrapper) {
                    logger.info("极光推送 = {}: ", responseWrapper.responseContent);
                }
            });
        } catch (URISyntaxException e) {
            logger.error(e.getMessage(), e);
        }

    }

    /**
     * 发给所有用户
     *
     * @param content
     */
    public static void sendPushAll(String content) {
        sendPushAll(content, null);
    }

    public static void sendPushAll(String content, Map<String, String> extras) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        String host = (String) clientConfig.get(ClientConfig.PUSH_HOST_NAME);
        String basicAuthorization = ServiceHelper.getBasicAuthorization(jpushConfigurer.getAppkey(), jpushConfigurer.getSecret());
        final NettyHttpClient client = new NettyHttpClient(basicAuthorization, null, clientConfig);
        try {
            URI uri = new URI(host + clientConfig.get(ClientConfig.PUSH_PATH));
            PushPayload payload = buildPushObjectAllAliasAlert(content, extras);
            client.sendRequest(HttpMethod.POST, payload.toString(), uri, new NettyHttpClient.BaseCallback() {
                @Override
                public void onSucceed(ResponseWrapper responseWrapper) {
                    logger.info("极光推送 = {}: ", responseWrapper.responseContent);
                }
            });
        } catch (Exception e) {
            logger.error("极光消息推送失败", e);
        }
    }

    /**
     * 向安卓和ios同时推送消息
     *
     * @param content
     * @param extras  参数
     * @return
     */
    private static PushPayload buildPushObjectAllAliasAlert(String content, Map<String, String> extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                .setNotification(
                        Notification.newBuilder()

                                .addPlatformNotification(
                                        AndroidNotification.newBuilder()
                                                .addExtras(extras)
                                                .setAlert(content)
                                                .build())
                                .addPlatformNotification(
                                        IosNotification.newBuilder()
                                                .addExtras(extras)
                                                .setAlert(content)
                                                .build())
                                .build())
                .setOptions(Options.newBuilder().setApnsProduction(jpushConfigurer.isApns()).build())
                .build();
    }

    /***
     *
     * @param alias 指定推送给哪个用户
     * @param content 内容
     * @param extras 参数
     * @return
     */
    private static PushPayload buildPushObjectAllAliasAlert(String alias, String content, Map<String, String> extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(
                        Notification.newBuilder()
                                .addPlatformNotification(
                                        AndroidNotification.newBuilder()
                                                .addExtras(extras)
                                                .setAlert(content)
                                                .build())
                                .addPlatformNotification(
                                        IosNotification.newBuilder()
                                                .addExtras(extras)
                                                .setAlert(content)
                                                .build())
                                .build())
                .setOptions(Options.newBuilder().setApnsProduction(jpushConfigurer.isApns()).build())
                .build();
    }

}
