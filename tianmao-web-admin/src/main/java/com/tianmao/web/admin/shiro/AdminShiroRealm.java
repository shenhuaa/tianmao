
package com.tianmao.web.admin.shiro;
import com.tianmao.common.CurrentUser;
import com.tianmao.service.admin.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * web-admin 安全认证
 *
 * @author roach
 * @date 2017/11/10
 */

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import com.tianmao.model.user.Admin;
import com.tianmao.service.admin.PermissionService;
import com.tianmao.type.user.UserStatus;

public class AdminShiroRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(AdminShiroRealm.class);

    @Autowired
    private AdminService adminService;

    @Autowired
    private PermissionService permissionService;


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token == null) {
            return null;
        }
        String username = (String) token.getPrincipal();
        if (logger.isDebugEnabled()) {
            logger.debug("用户[{}]正尝试登录..", username);
        }
        Admin admin = adminService.getAdminByUsername(username);
        if (null == admin) {
            logger.warn("后台用户[{}]不存在", username);
            throw new UnknownAccountException();
        } else if (admin.getStatus() == UserStatus.FREEZE) {
            logger.warn("后台用户[{}]已被禁用", username);
            throw new DisabledAccountException();
        }

        //把用户放到shiro中资料
        CurrentUser currentUser = CurrentUser.builder()
                .id(admin.getId())
                .username(admin.getMobile())
                .build();
        logger.debug("登录用户资料:{}", currentUser);

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                currentUser,
                admin.getPassword().toCharArray(),
                ByteSource.Util.bytes(admin.getPasswordSalt()),
                getName()
        );
        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        CurrentUser currentUser = (CurrentUser) getAvailablePrincipal(principals);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permissionService.getPermissionMarksByAdminId(currentUser.getId()));
        if (0 == info.getStringPermissions().size()) {
            logger.debug("当前用户[{}]无任何权限", currentUser.getUsername());
        }
        return info;
    }

}

