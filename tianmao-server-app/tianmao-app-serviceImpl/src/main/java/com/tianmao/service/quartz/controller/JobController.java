package com.tianmao.service.quartz.controller;

import com.tianmao.api.quartz.JobClient;
import com.tianmao.service.common.HttpCode;
import com.tianmao.service.exception.BaseServiceException;
import com.tianmao.service.model.quartzs.QuartzTask;
import com.tianmao.service.quartz.JobService;
import com.tianmao.service.quartz.base.ExecuteQuartzTask;
import com.tianmao.service.type.quartz.TaskGroup;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * job服务接口
 *
 * @author roach
 * @date 2018/3/14
 */
@RestController
public class JobController implements JobClient {

    @Autowired
    private JobService jobService;

    @Override
    public void addJob(@RequestBody QuartzTask quartzTask) {

        jobService.addJob(quartzTask);
    }

    @Override
    public void addJobs(@RequestBody List<QuartzTask> quartzTasks) {

        jobService.addJobs(quartzTasks);
    }

    @Override
    public boolean stopJob(Long taskId, @RequestBody TaskGroup group) {
        return jobService.stopJob(taskId,group);
    }

    @Override
    public boolean stopJobs(@RequestBody QuartzTask quartzTask) {
        return jobService.stopJobs(quartzTask);
    }
}


