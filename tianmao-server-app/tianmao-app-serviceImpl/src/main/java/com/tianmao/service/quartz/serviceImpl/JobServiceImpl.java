package com.tianmao.service.quartz.serviceImpl;

import com.tianmao.service.quartz.base.ExecuteQuartzTask;
import com.tianmao.utils.HttpCode;
import com.tianmao.service.model.quartzs.QuartzTask;
import com.tianmao.service.exception.BaseServiceException;
import com.tianmao.service.quartz.JobService;
import com.tianmao.service.type.quartz.TaskGroup;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import java.util.List;

/**
 * job服务接口
 *
 * @author roach
 * @date 2018/3/14
 */
@RestController
public class JobServiceImpl implements JobService {

    private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

    @Autowired
    private Scheduler scheduler;

    @Override
    public void addJob(@RequestBody QuartzTask quartzTask) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("quartzTask", quartzTask);
        String groupName = quartzTask.getTaskGroup().name();
        JobKey jobKey = new JobKey(String.valueOf(quartzTask.getId()), groupName);
        JobDetail jobDetail = JobBuilder.newJob(ExecuteQuartzTask.class)
                .withIdentity(jobKey)
                .usingJobData("id", quartzTask.getId())
                .usingJobData(jobDataMap)
                .build();

        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(String.valueOf(quartzTask.getId()), groupName)
                .withSchedule(simpleSchedule())
                .startAt(quartzTask.getStartTime())
                .build();
        try {
            if (scheduler.checkExists(jobKey)) {
                logger.debug("任务[{}]已运行中", jobKey);
                return;
            }
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new BaseServiceException(HttpCode.ERROR, "添加任务调度失败");
        }
    }

    @Override
    public void addJobs(@RequestBody List<QuartzTask> quartzTasks) {
        if (quartzTasks.size() == 0) {

            throw new BaseServiceException(HttpCode.ERROR, "任务集合不能为空");
        }
        for (QuartzTask quartzTask : quartzTasks) {
            this.addJob(quartzTask);
        }
    }

    /**
     * 停止指定的一个定时任务
     *
     * @return
     */
    @Override
    public boolean stopJob(Long taskId, @RequestBody TaskGroup group) {
        JobKey jobKey = new JobKey(String.valueOf(taskId), group.name());
        try {
            return scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            throw new BaseServiceException(HttpCode.ERROR, "任务调度删除失败");
        }


    }

    /**
     * 停止指定的定时任务集合
     *
     * @param quartzTask
     * @return
     */
    @Override
    public boolean stopJobs(@RequestBody QuartzTask quartzTask) {
        boolean result = false;
        JobKey jobKey = new JobKey(String.valueOf(quartzTask.getId()), quartzTask.getTaskGroup().name());
        try {
            result = scheduler.deleteJob(jobKey);
            logger.debug("任务调度删除成功：[{}]", quartzTask);
        } catch (SchedulerException e) {
            logger.error("任务调度删除失败：[{}]", quartzTask, e);
        }
        return result;
    }


}


