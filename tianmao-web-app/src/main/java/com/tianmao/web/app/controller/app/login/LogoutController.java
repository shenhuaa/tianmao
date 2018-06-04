package com.tianmao.web.app.controller.app.login;
import com.tianmao.common.HttpCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tianmao.web.app.token.UserTokenManager;
import com.tianmao.web.app.util.Rest;

/**
 * 退出登陆控制器
 */
@RequestMapping("/logout")
@RestController
public class LogoutController {

    @Autowired
    private UserTokenManager userTokenManager;


    /**
     * 登出
     * @param userId 账号
     */
    @RequestMapping
    public Rest logout(Long userId) {
        Rest.Builder rest = Rest.newBuilder();
        if (userId == null) {
            return rest.code(HttpCode.MISSING_PARAMETERS).build();
        }
        userTokenManager.deleteToken(userId);
        return rest.code(HttpCode.OK).build();
    }

}