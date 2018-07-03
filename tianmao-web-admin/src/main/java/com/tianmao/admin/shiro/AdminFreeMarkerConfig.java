package com.tianmao.admin.shiro;

import com.tianmao.admin.freemarker.FreeMarkerConfig;
import com.tianmao.admin.tags.SidebarMenusTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * FreeMarker配置
 * Created by roach on 2017/6/5.
 */
@Configuration
public class AdminFreeMarkerConfig extends FreeMarkerConfig {

    @Autowired
    private SidebarMenusTag sidebarMenusTag;

    @Autowired
    private ShiroTags shiroTags;


    @Override
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        Map<String, Object> freemarkerVariables = new HashMap<>();
        freemarkerVariables.put("sidebar_menus", sidebarMenusTag);
        freemarkerVariables.put("shiro", shiroTags);
        FreeMarkerConfigurer configurer = super.freeMarkerConfigurer();
        configurer.setFreemarkerVariables(freemarkerVariables);
        return configurer;
    }

    @Bean
    public ShiroTags shiroTags() {
        return new ShiroTags();
    }
}