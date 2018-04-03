package tianmao.web.app.controller.app.login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tianmao.common.HttpCode;
import tianmao.model.user.AccountBind;
import tianmao.model.user.User;
import tianmao.service.AccountBindService;
import tianmao.service.UserService;
import tianmao.type.user.AccountType;
import tianmao.type.user.UserStatus;
import tianmao.web.app.util.Rest;

/**
 * 微信绑定
 *
 * @author roach
 * @date 2017/11/27
 */
@RequestMapping("/weixin/login")
@RestController
public class WeixinLoginController {

    private final AccountBindService accountBindService;
    private final LoginComponent loginComponent;
    private final UserService userService;

    @Autowired
    public WeixinLoginController(AccountBindService accountBindService, LoginComponent loginComponent, UserService userService) {
        this.accountBindService = accountBindService;
        this.loginComponent = loginComponent;
        this.userService = userService;
    }


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
        AccountBind accountBind = accountBindService.getAccountBindByOpenId(openid, AccountType.WEIXIN_ACCOUNT);
        if (accountBind == null) {
            return rest.code(HttpCode.WEIXIN_ACCOUNT_NO_BIND).message("微信账号未绑定").build();
        }
        User user = userService.getUserById(accountBind.getUserId());
        if (user.getStatus() == UserStatus.FREEZE) {
            return rest.code(HttpCode.ACCOUNT_ALREADY_FREEZE).message("账号已被冻结,请联系客服").build();
        }
        return loginComponent.login(user);
    }

}
