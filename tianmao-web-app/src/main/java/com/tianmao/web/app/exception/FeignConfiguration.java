package com.tianmao.web.app.exception;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * FeignErrorDecoder 配置
 *
 * @author roach
 * @date 2017/11/29
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public FeignErrorDecoder feignErrorDecoder() {
        return new FeignErrorDecoder();
    }

    /**
     * 超时配置
     *
     * @param env
     * @return
     */
    @Bean
    Request.Options requestOptions(ConfigurableEnvironment env) {
        //设置不超时
        int ribbonReadTimeout = env.getProperty("ribbon.ReadTimeout", int.class, 60 * 1000 * 100);
        int ribbonConnectionTimeout = env.getProperty("ribbon.ConnectTimeout", int.class, 10 * 1000 * 100);
        return new Request.Options(ribbonConnectionTimeout, ribbonReadTimeout);
    }

}
