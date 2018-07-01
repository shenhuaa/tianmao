package com.tianmao.api.quartz;

import com.tianmao.service.dto.mall.QuartzTaskDtoFilter;
import com.tianmao.service.model.quartzs.QuartzTask;
import com.tianmao.service.type.quartz.TaskGroup;
import com.tianmao.service.type.quartz.TaskStatus;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 任务调度服务接口
 *
 * @author roach
 * @date 2018/3/14
 */
@FeignClient(name = "${server.app.name}")
public interface QuartzTaskClient {

    @RequestMapping(value = "/quartz/task/create")
    boolean create(QuartzTask quartzTask);

    @RequestMapping(value = "/quartz/task/getById")
    QuartzTask getById(@RequestParam("id") Long id);

    @RequestMapping(value = "/quartz/task/getListByModule")
    List<QuartzTask> getListByModule(@RequestParam("module") String module, QuartzTaskDtoFilter filter);

    @RequestMapping(value = "/quartz/task/totalByModule")
    int totalByModule(@RequestParam("module") String module, TaskStatus taskStatus);

    /**
     * 修改任务状态
     *
     * @param taskId
     * @param taskStatus
     * @return
     */
    @RequestMapping(value = "/quartz/task/updateStatus")
    boolean updateStatus(@RequestParam("taskId") Long taskId, TaskStatus taskStatus);

    /**
     * 取消任务调度
     * @return
     */
    @RequestMapping(value = "/quartz/task/updateStopJob")
    boolean updateStopJob(@RequestParam("dataId") Long dataId, TaskGroup group);

    /**
     * 获取一个任务
     *
     * @return
     */
    @RequestMapping(value = "/quartz/task/getQuartzTaskByOrderId")
    QuartzTask getQuartzTaskByOrderId(@RequestParam("orderId") Long orderId);

}
