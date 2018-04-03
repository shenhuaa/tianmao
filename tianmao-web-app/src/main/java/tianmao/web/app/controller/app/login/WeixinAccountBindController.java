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
import tianmao.service.VerificationCodeService;
import tianmao.type.VerificationCodeType;
import tianmao.type.user.AccountType;
import tianmao.type.user.BindType;
import tianmao.web.app.sencode.SendCodeComponent;
import tianmao.web.app.util.Rest;
import tianmao.web.app.util.ValidateUtil;

import java.util.Date;

/**
 * 微信账号绑定
 *
 * @author roach
 * @date 2017/12/13
 */
@RequestMapping(value = "/weixin/account")
@RestController
public class WeixinAccountBindController {

    private final SendCodeComponent sendCodeComponent;
    private final VerificationCodeService verificationCodeService;
    private final UserService userService;
    private final AccountBindService accountBindService;
    private final LoginComponent loginComponent;

    @Autowired
    public WeixinAccountBindController(SendCodeComponent sendCodeComponent, VerificationCodeService verificationCodeService,
                                       UserService userService, AccountBindService accountBindService, LoginComponent loginComponent) {
        this.userService = userService;
        this.sendCodeComponent = sendCodeComponent;
        this.accountBindService = accountBindService;
        this.verificationCodeService = verificationCodeService;
        this.loginComponent = loginComponent;
    }

    /**
     * 获取验证码
     *
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/bind/code")
    public Rest bindCode(String mobile) {
        return  sendCodeComponent.send(mobile, VerificationCodeType.APP_ACCOUNT_BIND_MOBILE);
    }

    /**
     * 判断手机号是否存在
     *
     * @param mobile
     * @return
     */
    @PostMapping(value = "/check/mobile")
    public Rest mobile(String mobile) {
        Rest.Builder rest = Rest.newBuilder();
        if (StringUtils.isEmpty(mobile)) {
            return rest.code(HttpCode.MISSING_PARAMETERS).message("手机不能为空").build();
        }
        if (!ValidateUtil.isMobile(mobile)) {
            return rest.code(HttpCode.ILLEGAL_PARAMETERS).message("手机格式有误").build();
        }
        User user = userService.getUserByUsername(mobile);
        if (user == null) {
            return rest.code(HttpCode.MOBILE_NO_REGISTERED_NORNAL).message("手机未注册").build();
        }
        return rest.code(HttpCode.MOBILE_ALREADY_REGISTERED_NORNAL).message("手机已注册").build();
    }

    /**
     * 已有账号绑定
     *
     * @param mobile     手机号
     * @param password   密码,非必填
     * @param openid     唯一标识
     * @param code       验证码
     * @param nickname   微信用户昵称
     * @param headImgUrl 微信用户头像
     * @return
     */
    @PostMapping(value = "/bind")
    public Rest bind(String mobile, String password, Integer code, String openid, String nickname, String headImgUrl) {
        Rest.Builder rest = Rest.newBuilder();
        if (StringUtils.isEmpty(mobile)) {
            return rest.code(HttpCode.MISSING_PARAMETERS).message("手机不能为空").build();
        }
        if (code == null) {
            return rest.code(HttpCode.MISSING_PARAMETERS).message("code不能为空").build();
        }
        if (StringUtils.isEmpty(openid)) {
            return rest.code(HttpCode.ILLEGAL_PARAMETERS).message("openid不能为空").build();
        }
        if (!ValidateUtil.isMobile(mobile)) {
            return rest.code(HttpCode.ILLEGAL_PARAMETERS).message("手机格式有误").build();
        }
        User user = userService.getUserByUsername(mobile);
        if (user == null) {
            if (StringUtils.isEmpty(password)) {
                return rest.code(HttpCode.MISSING_PARAMETERS).message("密码不能为空").build();
            } else if (password.length() < 6 || password.length() > 20) {
                return rest.code(HttpCode.ILLEGAL_PARAMETERS).message("密码长度不能小于6或大于20位").build();
            }
            if (StringUtils.isEmpty(nickname)) {
                return rest.code(HttpCode.MISSING_PARAMETERS).message("用户昵称不能为空").build();
            }
           /* if (StringUtils.isEmpty(headImgUrl)) {
                return rest.code(HttpCode.MISSING_PARAMETERS).message("用户头像不能为空").build();
            }*/
        }

        //验证验证码
        verificationCodeService.verification(mobile, code);

        //绑定对象
        AccountBind accountBind = AccountBind.builder()
                .userId(user == null ? null : user.getId())
                .openid(openid)
                .bindType(user == null ? BindType.ACCOUNT_REGISTER_BIND : BindType.ACCOUNT_BIND)
                .bindTime(new Date())
                .accountType(AccountType.WEIXIN_ACCOUNT)
                .build();

        if (user == null) {
            //注册并绑定账号
            user = registerAccountBind(mobile, password, nickname, headImgUrl, accountBind);
        } else {
            //绑定账号
            accountBindService.create(accountBind);
            user = userService.getUserById(accountBind.getUserId());
        }

        //删除验证码
        verificationCodeService.destroy(mobile, code);

        return loginComponent.login(user);
    }

    /**
     * 注册并绑定账号
     *
     * @param mobile
     * @param password
     * @param nickname
     * @param headImgUrl
     * @param accountBind
     */
    private User registerAccountBind(String mobile, String password, String nickname, String headImgUrl, AccountBind accountBind) {
        return userService.registerAccountBind(User.builder()
                .mobile(mobile)
                .password(password)
                .nickname(nickname)
                .headImgUrl(headImgUrl)
                .accountBind(accountBind)
                .build());

    }

}
