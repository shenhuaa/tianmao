package com.tianmao.admin;

import com.tianmao.utils.ServletRequestAttributesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * 获取国际化文件工具类
 *
 * @author roach
 * @date 2018/4/20
 */
@Component
public final class I18nMessageUtil {


    public static final String PARAMETER_LANG = "lang";
    public static final String PARAMETER_NATIONID = "nationId";

    /**
     * 默认语言
     */
    public static final String DEFAULT_LANG = "zh_CN";

    @Autowired
    private MessageSource message;

    private static MessageSource messageSource;

    private I18nMessageUtil() {

    }

    @PostConstruct
    public void init() {
        messageSource = this.message;
    }

    /**
     * 根据code获取内容
     *
     * @param code
     * @return
     */
    public static String getMessage(String code) {
        return getMessage(code, null);
    }

    /**
     * 根据code和参数获取内容
     *
     * @param code
     * @param args
     * @return
     */
    public static String getMessage(String code, Object[] args) {
        HttpServletRequest request = ServletRequestAttributesUtil.getHttpServletRequest();
        String lang = request.getParameter(PARAMETER_LANG);

        if (!StringUtils.hasText(lang)) {
            //service层从header中获取
            lang = request.getHeader(PARAMETER_LANG);
        }
        Locale locale = Locale.SIMPLIFIED_CHINESE;
        if ("en".equalsIgnoreCase(lang)) {
            locale = Locale.ENGLISH;
        }

        return messageSource.getMessage(code, args, locale);
    }

    /**
     * 获取请求的语种参数
     *
     * @return
     */
    public static Locale getCurrentLang() {
        HttpServletRequest request = ServletRequestAttributesUtil.getHttpServletRequest();
        String lang = request.getParameter(PARAMETER_LANG);
        Locale locale = Locale.SIMPLIFIED_CHINESE;
        if (!StringUtils.hasText(lang)) {
            //service层从header中获取
            lang = request.getHeader(PARAMETER_LANG);
        }
        if ("en".equalsIgnoreCase(lang)) {
            locale = Locale.ENGLISH;
        }
        return locale;
    }

    /**
     * 获取nationId
     *
     * @return
     */
    public static Integer getCountryShortCode() {
        HttpServletRequest request = ServletRequestAttributesUtil.getHttpServletRequest();
        String nationId = request.getParameter(PARAMETER_NATIONID);
        if (null == nationId || "1".equals(nationId)) {
            return 1;
        }
        return 2;
    }

}
