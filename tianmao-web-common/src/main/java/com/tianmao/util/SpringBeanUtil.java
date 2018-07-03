package com.tianmao.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring功能类，用于获取bean
 *
 * @author roach
 * @date 2018/5/12
 */
@Component
public class SpringBeanUtil implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(SpringBeanUtil.class);

    private static ApplicationContext ctx = null;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        SpringBeanUtil.ctx = ctx;
    }

    public static ApplicationContext getApplicationContext() {
        return ctx;
    }

    public static <T> T getBean(String prop) {
        Object obj = ctx.getBean(prop);
        if (logger.isDebugEnabled()) {
            logger.debug("property=[" + prop + "],object=[" + obj + "]");
        }
        return (T) obj;
    }

}
