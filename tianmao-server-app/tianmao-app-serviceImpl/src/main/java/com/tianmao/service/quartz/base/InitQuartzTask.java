package com.tianmao.service.quartz.base;
import com.tianmao.utils.PagingAttribute;
import com.tianmao.service.constant.ModuleConstant;
import com.tianmao.service.dto.mall.QuartzTaskDtoFilter;
import com.tianmao.service.model.quartzs.QuartzTask;
import com.tianmao.service.quartz.JobService;
import com.tianmao.service.quartz.QuartzTaskService;
import com.tianmao.service.type.quartz.TaskStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 系统启动时加载任务调度
 *
 * @author roach
 * @date 2018/3/15
 */
@Component
public class InitQuartzTask implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitQuartzTask.class);

    @Autowired
    private JobService jobService;


    @Autowired
    private QuartzTaskService quartzTaskService;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void run(ApplicationArguments args) {
        threadPoolTaskExecutor.execute(() -> {
            try {
                final int total = quartzTaskService.total(TaskStatus.RUNNING);
                final int page = (int) Math.ceil(total / 500F);

                logger.info("任务加载总条数：[{}]", total);
                for (int i = 0; i < page; i++) {
                    QuartzTaskDtoFilter filter = new QuartzTaskDtoFilter();
                    filter.setStatus(TaskStatus.RUNNING);
                    filter.setPagingAttribute(new PagingAttribute(i, 500));
                    List<QuartzTask> tasks = quartzTaskService.getList(filter);
                    if (!tasks.isEmpty()) {
                        jobService.addJobs(tasks);
                    }
                }
            } catch (Exception e) {
                logger.error("任务调度加载失败：", e);
            }
        });
    }

}
