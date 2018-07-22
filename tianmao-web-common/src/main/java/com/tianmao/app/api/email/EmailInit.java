package com.tianmao.app.api.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * 系统启动时加载任务调度
 *
 * @author roach
 * @date 2018/3/15
 */
//implements ApplicationRunner
public class EmailInit   {
    private static final Logger logger = LoggerFactory.getLogger(EmailInit.class);

    @Autowired
    private EmailGood emailGood;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    public void run(ApplicationArguments args) {
        threadPoolTaskExecutor.execute(() -> {
            try {
                System.out.println("系统启动时候打印---------------------------------------------------------------");
                emailGood.sendTemplateMail("171375063@qq.com",552211);
            } catch (Exception e) {
                logger.error("自动调用发邮件失败：", e);
            }
        });
    }
}
