package com.tianmao.web.admin.freemarker;
import freemarker.template.SimpleHash;

/**
 * shiro ftl 标签
 * <p>
 * Created by roach on 2017/6/5.
 */
public class ShiroTags extends SimpleHash {

    public ShiroTags() {
        put("guest", new GuestTag());
        put("user", new UserTag());
        put("hasRole", new HasRoleTag());
        put("lacksRole", new LacksRoleTag());
        put("principal", new PrincipalTag());
        put("hasAnyRoles", new HasAnyRolesTag());
        put("authenticated", new AuthenticatedTag());
        put("hasPermission", new HasPermissionTag());
        put("lacksPermission", new LacksPermissionTag());
        put("notAuthenticated", new NotAuthenticatedTag());
    }
}