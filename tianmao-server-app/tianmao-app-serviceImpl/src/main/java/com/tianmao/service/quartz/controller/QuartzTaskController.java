package com.tianmao.service.quartz.controller;

import com.tianmao.api.quartz.QuartzTaskClient;
import com.tianmao.service.dto.mall.QuartzTaskDtoFilter;
import com.tianmao.service.model.quartzs.QuartzTask;
import com.tianmao.service.type.quartz.TaskGroup;
import com.tianmao.service.type.quartz.TaskStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 任务调度服务接口
 *
 * @author roach
 * @date 2018/3/14
 */
@RestController
public class QuartzTaskController implements QuartzTaskClient {

    @Override
    public boolean create(QuartzTask quartzTask) {
        return false;
    }

    @Override
    public QuartzTask getById(Long id) {
        return null;
    }

    @Override
    public List<QuartzTask> getListByModule(String module, QuartzTaskDtoFilter filter) {
        return null;
    }

    @Override
    public int totalByModule(String module, TaskStatus taskStatus) {
        return 0;
    }

    @Override
    public boolean updateStatus(Long taskId, TaskStatus taskStatus) {
        return false;
    }

    @Override
    public boolean updateStopJob(Long dataId, TaskGroup group) {
        return false;
    }

    @Override
    public QuartzTask getQuartzTaskByOrderId(Long orderId) {
        return null;
    }
}
