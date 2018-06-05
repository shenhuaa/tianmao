package com.tainmao.service.base;

import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * job服务接口
 *
 * @author roach
 * @date 2018/3/14
 */
@EnableConfigurationProperties(QuartzProperties.class)
@Configuration
public class QuartzConfigJob {

    private static final Logger logger = LoggerFactory.getLogger(QuartzConfigJob.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private QuartzProperties quartzProperties;

    @Autowired
    private QuartzJobFactory quartzJobFactory;

    @Bean
    public Scheduler scheduler() throws IOException {
        return schedulerFactoryBean().getScheduler();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //用于quartz集群,QuartzScheduler 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setQuartzProperties(quartzProperties());
        schedulerFactoryBean.setJobFactory(quartzJobFactory);
        schedulerFactoryBean.setDataSource(dataSource);
        //QuartzScheduler 延时启动，应用启动完10秒后 QuartzScheduler 再启动
        schedulerFactoryBean.setStartupDelay(quartzProperties.getStartupDelay());
        return schedulerFactoryBean;
    }

    @Bean
    public Properties quartzProperties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        Properties properties = new Properties();
        properties.setProperty("org.quartz.scheduler.instanceId", quartzProperties.getInstanceId());
        properties.setProperty("org.quartz.scheduler.instanceName", quartzProperties.getInstanceName());
        properties.setProperty("org.quartz.threadPool.threadCount", quartzProperties.getThreadCount());
        properties.setProperty("org.quartz.scheduler.skipUpdateCheck", quartzProperties.getSkipUpdateCheck());
        properties.setProperty("org.quartz.jobStore.class", quartzProperties.getJobStoreClass());
        properties.setProperty("org.quartz.jobStore.driverDelegateClass", quartzProperties.getDriverDelegateClass());
        properties.setProperty("org.quartz.jobStore.dataSource", quartzProperties.getDataSource());
        properties.setProperty("org.quartz.jobStore.tablePrefix", quartzProperties.getTablePrefix());
        properties.setProperty("org.quartz.jobStore.isClustered", quartzProperties.getIsClustered());
        propertiesFactoryBean.setProperties(properties);
        try {
            propertiesFactoryBean.afterPropertiesSet();
            return propertiesFactoryBean.getObject();
        } catch (IOException e) {
            throw new RuntimeException("初始化quartz配置文件失败", e);
        }
    }

}
