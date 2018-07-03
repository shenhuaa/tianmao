package com.tianmao.admin.freemarker;
import org.apache.shiro.subject.Subject;

/**
 * Created by Administrator on 2016/4/14.
 */
public class HasAnyPermissionTag extends PermissionTag {

    private static final String OR_OPERATOR = ",";

    protected boolean showTagBody(String p) {
        boolean hasAnyPermission = false;
        Subject subject = getSubject();

        if (subject != null) {
            for (String role : p.split(OR_OPERATOR)) {
                if (subject.isPermitted(role.trim())) {
                    hasAnyPermission = true;
                    break;
                }
            }
        }
        return hasAnyPermission;
    }
}
