package com.tianmao.service.quartz.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.tianmao.utils.HttpCode;
import com.tianmao.utils.PagingAttribute;
import com.tianmao.service.dto.mall.QuartzTaskDtoFilter;
import com.tianmao.service.model.quartzs.QuartzTask;
import com.tianmao.service.quartz.mapper.QuartzTaskMapper;
import com.tianmao.service.exception.BaseServiceException;
import com.tianmao.service.quartz.JobService;
import com.tianmao.service.quartz.QuartzTaskService;
import com.tianmao.service.type.quartz.TaskGroup;
import com.tianmao.service.type.quartz.TaskStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * 任务调度服务接口
 *
 * @author roach
 * @date 2018/3/14
 */
@RestController
public class QuartzTaskServiceImpl implements QuartzTaskService {

    private static final Logger logger = LoggerFactory.getLogger(QuartzTaskServiceImpl.class);

    @Autowired
    private QuartzTaskMapper quartzTaskMapper;

    @Autowired
    private JobService jobService;

    @Override
    public boolean create(@RequestBody QuartzTask quartzTask) {
        Assert.notNull(quartzTask, "job对象不能为空");
        boolean result = quartzTaskMapper.create(quartzTask);
        jobService.addJob(quartzTask);
        return result;
    }

    @Override
    public QuartzTask getById(Long id) {
        Assert.notNull(id, "任务编号不能为空");
        return quartzTaskMapper.getById(id);
    }

    @Override
    public List<QuartzTask> getListByModule(String module, @RequestBody QuartzTaskDtoFilter filter) {
        Assert.notNull(module, "项目模块名称不能为空");
        PagingAttribute page = filter.getPagingAttribute();
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        List<QuartzTask> quartzTaskList = quartzTaskMapper.getListByModule(module, filter);
        return quartzTaskList;
    }

    @Override
    public int totalByModule(String module, @RequestBody TaskStatus status) {
        Assert.notNull(module, "模块名称不能为空");
        return quartzTaskMapper.totalByModule(module, status);
    }

    @Override
    public boolean updateStatus(Long taskId, @RequestBody TaskStatus taskStatus) {
        Assert.notNull(taskId, "任务编号不能为空");
        Assert.notNull(taskStatus, "任务状态不能为空");
        boolean result = quartzTaskMapper.updateStatus(taskId, taskStatus, new Date());
        Assert.isTrue(result, "任务状态更新失败");
        return true;
    }

    /**
     * 订单调度停止
     *
     * @param dataId
     * @return
     */
    @Override
    public boolean updateStopJob(Long dataId, @RequestBody TaskGroup group) {
        Assert.notNull(dataId, "dataId不能为空");
        Assert.notNull(group, "group不能为空");
        QuartzTask quartzTask = quartzTaskMapper.getByDataId(dataId, TaskStatus.RUNNING, group);
        if (quartzTask == null) {
            logger.error("dataId=[{}], taskGroup=[{}] 停止任务失败", dataId, group);
            return false;
        }
        jobService.stopJobs(quartzTask);
        return quartzTaskMapper.updateStopTask(quartzTask.getId());
    }

    /**
     * 获取一个任务
     *
     * @return
     */
    @Override
    public QuartzTask getQuartzTaskByOrderId(Long orderId) {
        QuartzTask quartzTask = quartzTaskMapper.getQuartzTask(orderId);
        if (quartzTask == null) {
            throw new BaseServiceException(HttpCode.ILLEGAL_PARAMETERS, "该任务订单不存在");
        }
        return quartzTask;
    }
}
