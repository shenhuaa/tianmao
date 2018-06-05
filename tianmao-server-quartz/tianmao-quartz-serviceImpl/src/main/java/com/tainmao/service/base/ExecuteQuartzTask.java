package com.tainmao.service.base;

import com.tianmao.model.quartzs.QuartzTask;
import com.tianmao.service.QuartzTaskService;
import com.tianmao.type.quartz.TaskGroup;
import com.tianmao.type.quartz.TaskStatus;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 执行任务调度
 *
 * @author roach
 * @date 2018/3/14
 */
@Component
public class ExecuteQuartzTask implements Job {

    private static final Logger logger = LoggerFactory.getLogger(ExecuteQuartzTask.class);


    @Autowired
    private QuartzTaskService quartzTaskService;



    @Override
    public void execute(JobExecutionContext context) {
        logger.debug("quartzJob开始执行任务");
        final JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        QuartzTask quartzTask = (QuartzTask) dataMap.get("quartzTask");
        QuartzTask dbQuartzTask = quartzTaskService.getById(quartzTask.getId());
        if (TaskStatus.RUNNING != dbQuartzTask.getStatus()) {
            logger.warn("quartzJob停止执行任务");
            return;
        }
        if (quartzTask.getTaskGroup() == TaskGroup.ADMIN_MESSAGE_SEND) {
            //处理任务调度业务逻辑
        }
        //修改任务状态
        quartzTaskService.updateStatus(quartzTask.getId(), TaskStatus.COMPLETE);

        logger.debug("quartzJob完成执行任务：[{}]", quartzTask);
    }


}
