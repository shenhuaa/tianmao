package com.tianmao.service.quartz;

import com.tianmao.service.model.quartzs.QuartzTask;
import com.tianmao.service.type.quartz.TaskGroup;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * job服务接口
 *
 * @author roach
 * @date 2018/3/14
 */
public interface JobService {

    /**
     * 添加任务
     *
     * @param quartzTask
     */
    void addJob(QuartzTask quartzTask);

    /**
     * 添加批量任务
     *
     * @param quartzTasks
     */
    void addJobs(List<QuartzTask> quartzTasks);


    /**
     * 停止任务
     * @param taskId
     * @param group
     * @return
     */
    boolean stopJob(@RequestParam("taskId") Long taskId, TaskGroup group);

    /**
     * 停止任务
     * @param quartzTask
     * @return
     */
    boolean stopJobs(QuartzTask quartzTask);


}

