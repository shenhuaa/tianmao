package com.tianmao.service.common;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 获取当前登陆用户信息
 *
 * @author roach
 * @date 2017/11/15
 */
public final class CurrentUserUtils {

    private static final Logger logger = LoggerFactory.getLogger(CurrentUserUtils.class);

    /**
     * 判断用户是否已经登陆
     *
     * @return
     */
    public static boolean isLogin() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
            return true;
        }
        return false;
    }

    /**
     * 获取登陆用户对象
     *
     * @return
     */
    public static CurrentUser getCurrent() {
        CurrentUser currentUser = (CurrentUser) SecurityUtils.getSubject().getPrincipal();
        if (null == currentUser || currentUser.getId() == null) {
            logger.error("用户未登录，无法获取'登录用户'ID");
            throw new RuntimeException("用户未登录，无法获取'登录用户'ID");
        }
        return currentUser;
    }

    /**
     * 获取登陆用户id
     *
     * @return
     */
    public static Long getCurrentUserId() {
        CurrentUser current = getCurrent();
        return current.getId();
    }

    /**
     * 获取app端的token
     *
     * @return
     */
    public static String getToken() {
        CurrentUser current = getCurrent();
        String token = current.getToken();
        if (!StringUtils.hasText(token)) {
            logger.error("用户未登录，无法获取token");
            throw new RuntimeException("用户未登录，无法获取token");
        }
        return token;
    }
}