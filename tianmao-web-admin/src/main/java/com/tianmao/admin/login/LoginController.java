package com.tianmao.admin.login;

import com.tianmao.admin.I18nMessageUtil;
import com.tianmao.utils.HttpCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tianmao.util.DeviceTypeUtil;
import com.tianmao.util.Rest;

/**
 * 用户登陆
 * Created by roach on 2017/5/31.
 */
@Controller
public class LoginController extends LoginCommonComponent {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 跳转PC端登录页面
     *
     * @return
     */
    @GetMapping(value = "/login", produces = MediaType.TEXT_HTML_VALUE)
    public String login() {
        if (DeviceTypeUtil.isMobile()) {
            return "redirect:/mobile/login";
        }
        return "login";
    }

    /**
     * 跳转手机H5登录页面
     *
     * @return
     */
    @GetMapping(value = "/mobile/login", produces = MediaType.TEXT_HTML_VALUE)
    public String mobileLogin() {
        return "/mobileshop/mobile-login";
    }


    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Rest login(@RequestParam String username, @RequestParam String password) {
        Rest.Builder rest = Rest.newBuilder();
        Subject subject = SecurityUtils.getSubject();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, false);
            subject.login(token);
            logger.debug("用户[{}]登录成功", username);
            return rest.code(true).message(I18nMessageUtil.getMessage("login.success")).build();
        } catch (IncorrectCredentialsException e) {
            logger.warn("[{}]密码不匹配", username);
            return rest.code(HttpCode.USERNAME_OR_PASSWORD_ERROR).message(I18nMessageUtil.getMessage("user.or.pwd.error")).build();
        } catch (UnknownAccountException e) {
            logger.warn("账号[{}]不存在", username);
            return rest.code(HttpCode.USERNAME_OR_PASSWORD_ERROR).message(I18nMessageUtil.getMessage("user.or.pwd.error")).build();
        } catch (DisabledAccountException e) {
            logger.warn("账号[{}]已被禁用", username);
            return rest.code(HttpCode.ACCOUNT_ALREADY_DISABLED).message(I18nMessageUtil.getMessage("account.alrady.disabled")).build();
        }
    }

}
