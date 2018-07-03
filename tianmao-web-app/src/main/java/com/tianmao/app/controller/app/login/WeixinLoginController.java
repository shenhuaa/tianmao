package com.tianmao.app.controller.app.login;

import com.tianmao.api.app.AccountBindClient;
import com.tianmao.api.app.UserClient;
import com.tianmao.utils.HttpCode;
import com.tianmao.service.model.user.AccountBind;
import com.tianmao.service.model.user.User;
import com.tianmao.service.type.user.AccountType;
import com.tianmao.service.type.user.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tianmao.util.Rest;

/**
 * 微信绑定
 *
 * @author roach
 * @date 2017/11/27
 */
@RequestMapping("/weixin/login")
@RestController
public class WeixinLoginController {

    @Autowired
    private AccountBindClient accountBindClient;
    @Autowired
    private  LoginComponent loginComponent;
    @Autowired
    private  UserClient userClient;




    /**
     * 绑定
     *
     * @param openid
     * @return
     */
    @PostMapping
    public Rest login(String openid) {

        Rest.Builder rest = Rest.newBuilder();
        if (StringUtils.isEmpty(openid)) {
            return rest.code(HttpCode.MISSING_PARAMETERS).message("绑定类型不能为空").build();
        }
        AccountBind accountBind = accountBindClient.getAccountBindByOpenId(openid, AccountType.WEIXIN_ACCOUNT);
        if (accountBind == null) {
            return rest.code(HttpCode.WEIXIN_ACCOUNT_NO_BIND).message("微信账号未绑定").build();
        }
        User user = userClient.getUserById(accountBind.getUserId());
        if (user.getStatus() == UserStatus.FREEZE) {
            return rest.code(HttpCode.ACCOUNT_ALREADY_FREEZE).message("账号已被冻结,请联系客服").build();
        }
        return loginComponent.login(user);
    }

}
