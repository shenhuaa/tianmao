package com.tianmao.app.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * AppWebMvcConfigurer
 *
 * @author roach
 * @date 2017/11/20
 */
@Configuration
public class AppWebMvcConfigurer extends WebMvcSupportConfigurer {

    @Bean
    public AuthenticationHandlerInterceptor authenticationHandlerInterceptor() {
        return new AuthenticationHandlerInterceptor();
    }

    /**
     * 拦截器
     *添加过滤放行的方法
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] excludePathPatterns = new String[]{
                "/user/common",
                "/user/**",
                "/error",
                "/404",
                "/swagger-resources/**", //接口文档放行
                "/swagger-ui.html",    //接口文档放行
                "/v2/api-docs",         //接口文档放行
                "/500",
                "/403",
                "/login",
                "/help"
        };
        registry.addInterceptor(authenticationHandlerInterceptor())
                .excludePathPatterns(excludePathPatterns);
    }

}

