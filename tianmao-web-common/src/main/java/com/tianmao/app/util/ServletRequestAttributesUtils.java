package com.tianmao.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 获取HttpServletRequest、HttpServletResponse、HttpSession工类具
 *
 * @author liwenjie
 * @date 2018/1/10
 */
public final class ServletRequestAttributesUtils {

    private static final Logger logger = LoggerFactory.getLogger(ServletRequestAttributesUtils.class);

    private ServletRequestAttributesUtils() {

    }

    public static HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getRequest();
    }

    public static HttpServletResponse getHttpServletResponse() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getResponse();
    }

    public static HttpSession getHttpSession() {
        HttpServletRequest request = getHttpServletRequest();
        return request.getSession();
    }
}