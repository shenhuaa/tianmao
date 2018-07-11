package com.tianmao.app.interceptor;

import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Properties;

/**
 * FreeMarker配置
 * Created by roach on 2017/6/5.
 */
@Configuration
public class FreeMarkerConfig extends FreeMarkerAutoConfiguration.FreeMarkerWebConfiguration {

    public FreeMarkerConfig () {
    }
    @Override
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = super.freeMarkerConfigurer();
        Properties settings = new Properties();
        settings.setProperty("classic_compatible", "true");
        settings.setProperty("template_update_delay", "0");
        settings.setProperty("number_format", "0.###");
        settings.setProperty("template_exception_handler", "ignore");
        configurer.setFreemarkerSettings(settings);
        return configurer;
    }

}