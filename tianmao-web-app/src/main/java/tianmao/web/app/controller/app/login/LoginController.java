package tianmao.web.app.controller.app.login;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tianmao.common.HttpCode;
import tianmao.common.Sha512HashPasswordUtil;
import tianmao.model.user.User;
import tianmao.service.app.UserService;
import tianmao.type.user.UserStatus;
import tianmao.web.app.util.Rest;

/**
 * 用户登陆
 *
 * @author roach
 * @date 2017/12/9
 */
@Api(description = "LoginController 用户登陆API")
@RequestMapping(value = "/login")
@RestController
public class LoginController {

    private final UserService userService;
    private final LoginComponent loginComponent;

    @Autowired
    public LoginController(UserService userService, LoginComponent loginComponent) {
        this.userService = userService;
        this.loginComponent = loginComponent;
    }


    /**
     * 手机账号登陆
     */
    @PostMapping
    public Rest login(String mobile, String password) {
        Rest.Builder rest = Rest.newBuilder();
        if (StringUtils.isEmpty(mobile)) {
            return rest.code(HttpCode.MISSING_PARAMETERS).message("手机不能为空").build();
        }
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            return rest.code(HttpCode.MISSING_PARAMETERS).message("密码不能为空").build();
        }
        User user = userService.getUserByUsername(mobile);
        if (user == null) {
            return rest.code(HttpCode.MOBILE_NO_REGISTERED).build();
        }
        if (user.getStatus() == UserStatus.FREEZE) {
            return rest.code(HttpCode.ACCOUNT_ALREADY_DISABLED).message("您好，该账号已被冻结，申请解冻请联系邮箱 vaishiduo@163.com").build();
        }
        boolean checkPassword = Sha512HashPasswordUtil.checkPassword(password, user.getPassword(), user.getPasswordSalt());
        if (!checkPassword) {
            return rest.code(HttpCode.USERNAME_OR_PASSWORD_ERROR).build();
        }
        if (user.getStatus() == UserStatus.FREEZE) {
            return rest.code(HttpCode.ACCOUNT_ALREADY_FREEZE).build();
        }
        return loginComponent.login(user);
    }

}
