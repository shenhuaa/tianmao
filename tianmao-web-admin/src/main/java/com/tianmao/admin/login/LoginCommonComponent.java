package com.tianmao.admin.login;

import com.tianmao.app.util.Rest;
import com.tianmao.utils.HttpCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * @author roach
 * @date 2018/03/30
 */
@Component
public class LoginCommonComponent {
    private static final Logger logger = LoggerFactory.getLogger(LoginCommonComponent.class);

    public Rest login(@RequestParam String username, @RequestParam String password) {
        Rest.Builder rest = Rest.newBuilder();
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, false);
            subject.login(token);
            logger.debug("用户[{}]登录成功", username);
            return rest.code(true).message("登录成功").build();
        } catch (IncorrectCredentialsException e) {
            logger.warn("[{}]密码不匹配", username);
            return rest.code(HttpCode.USERNAME_OR_PASSWORD_ERROR).message("账号或密码错误").build();
        } catch (UnknownAccountException e) {
            logger.warn("账号[{}]不存在", username);
            return rest.code(HttpCode.USERNAME_OR_PASSWORD_ERROR).message("账号或密码错误").build();
        } catch (DisabledAccountException e) {
            logger.warn("账号[{}]已被禁用", username);
            return rest.code(HttpCode.ACCOUNT_ALREADY_DISABLED).message("账号已被禁用").build();
        }
    }

}