package com.tainmao.service.base;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * quartz 配置文件
 *
 * @author roach
 * @date 2018/3/17
 */
@Data
@ConfigurationProperties(prefix = "quartz")
public class QuartzProperties {

    private String instanceId;

    private String instanceName;

    private String threadCount;

    private String skipUpdateCheck;

    private String jobStoreClass;

    private String driverDelegateClass;

    private String tablePrefix;

    private String dataSource;

    private String isClustered;

    private int startupDelay;
}
