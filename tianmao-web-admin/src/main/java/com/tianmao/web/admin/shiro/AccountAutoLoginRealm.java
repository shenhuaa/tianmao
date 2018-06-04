package com.tianmao.web.admin.shiro;
import com.tianmao.common.CurrentUser;
import com.tianmao.type.user.UserStatus;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import com.tianmao.model.user.User;
import com.tianmao.service.app.UserService;


/**
 * 用户自动登陆
 *
 * @author roach
 * @date 2017/12/21
 */
public class AccountAutoLoginRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(AccountAutoLoginRealm.class);

    @Autowired
    private UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token == null) {
            return null;
        }
        Object principal = token.getPrincipal();
        //门店自动登陆
        if (principal instanceof String) {
            return new SimpleAuthenticationInfo(CurrentUser.builder().build(), principal, getName());
        }
        Long username = (Long) token.getPrincipal();
        if (logger.isDebugEnabled()) {
            logger.debug("用户[{}]正尝试登录..", username);
        }
        User user = userService.getUserById(username);
        if (user == null) {
            logger.warn("用户[{}]不存在", username);
            throw new UnknownAccountException();
        } else if (user.getStatus() == UserStatus.FREEZE) {
            logger.warn("用户[{}]已被冻结", username);
            throw new LockedAccountException();
        }
        String nickname = StringUtils.isEmpty(user.getNickname()) ? user.getNickname() : user.getMobile();

        //用户资料
        CurrentUser currentUser = CurrentUser.builder()
                .id(user.getId())
                .username(user.getMobile())
                .nickname(nickname)
                .build();
        logger.debug("登录用户资料:{}", currentUser);
        return new SimpleAuthenticationInfo(currentUser, username, getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}
