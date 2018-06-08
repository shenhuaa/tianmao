package com.tianmao.service.quartz.base;
import com.tianmao.service.common.PagingAttribute;
import com.tianmao.service.constant.ModuleConstant;
import com.tianmao.service.dto.mall.QuartzTaskDtoFilter;
import com.tianmao.service.model.quartzs.QuartzTask;
import com.tianmao.service.quartz.JobService;
import com.tianmao.service.quartz.QuartzTaskService;
import com.tianmao.service.type.quartz.TaskStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class InitQuartzTask {

    private static final Logger logger = LoggerFactory.getLogger(InitQuartzTask.class);

    @Autowired
    private JobService jobService;


    @Autowired
    private QuartzTaskService quartzTaskService;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @PostConstruct
    public void init() {
        threadPoolTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //睡60秒，等服务启动完成再执行任务
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
                try {
                    String module = ModuleConstant.WEB_APP;
                    final int total = quartzTaskService.totalByModule(ModuleConstant.WEB_APP, TaskStatus.RUNNING);
                    final int page = (int) Math.ceil(total / 500F);

                    logger.debug("任务加载总条数：[{}]", total);
                    for (int i = 0; i < page; i++) {
                        QuartzTaskDtoFilter filter = new QuartzTaskDtoFilter();
                        filter.setStatus(TaskStatus.RUNNING);
                        filter.setPagingAttribute(new PagingAttribute(i, 500));
                        List<QuartzTask> tasks = quartzTaskService.getListByModule(module, filter);
                        if (tasks.size() > 0) {
                            jobService.addJobs(tasks);
                        }
                    }
                } catch (Exception e) {
                    logger.error("任务调度加载失败：", e);
                }
            }
        });
    }

}
