package com.tianmao.service.web.app.controller.app.login;

import com.tianmao.service.common.HttpCode;
import com.tianmao.service.web.app.util.Rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import com.tianmao.service.model.user.User;
import com.tianmao.service.web.app.token.UserTokenManager;

/**
 * 用户登陆
 *
 * @author roach
 * @date 2017/12/9
 */
@Component
public class LoginComponent {

    /**
     *天猫项目功能介绍
     * 1.极光推送
     *2.发短信
     * 3.上传图片
     * 4.抛异常
     * 5.拦截器
     * 6.微信授权登录
     * 7.上传视频..
     */

    @Autowired
    private UserTokenManager userTokenManager;



    /**
     * 手机账号登陆
     */
    public Rest login(User user) {
        Assert.notNull(user, "用户对象不能为空");
        Rest.Builder rest = Rest.newBuilder();
        //生成登陆token
        String token = userTokenManager.createToken(user.getId());
        return rest.code(HttpCode.OK)
                .put("token", token)
                .build();
    }

}
