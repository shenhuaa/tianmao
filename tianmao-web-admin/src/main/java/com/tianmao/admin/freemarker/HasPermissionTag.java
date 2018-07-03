package com.tianmao.admin.freemarker;
import org.apache.shiro.subject.Subject;

/**
 * <p>Equivalent to {@link org.apache.shiro.web.tags.HasPermissionTag}</p>
 *
 * @since 0.1
 */
public class HasPermissionTag extends PermissionTag {

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
