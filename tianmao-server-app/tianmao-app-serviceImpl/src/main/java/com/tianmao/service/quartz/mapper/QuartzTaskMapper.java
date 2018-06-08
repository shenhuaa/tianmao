package com.tianmao.service.quartz.mapper;

import com.tianmao.service.model.quartzs.QuartzTask;
import com.tianmao.service.mybatis.BaseMapper;
import com.tianmao.service.type.quartz.TaskGroup;
import com.tianmao.service.type.quartz.TaskStatus;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by roach on 2016/11/26.
 */
public interface QuartzTaskMapper extends BaseMapper<QuartzTask> {

    boolean create(QuartzTask quartzTask);

    QuartzTask getById(Long id);

    List<QuartzTask> getListByModule(@Param("module") String module, @Param("quartzTask") QuartzTask quartzTask);

    int totalByModule(@Param("module") String module, @Param("status") TaskStatus status);

    boolean updateStatus(@Param("taskId") Long taskId, @Param("taskStatus") TaskStatus taskStatus, @Param("updateTime") Date updateTime);

    boolean updateStopTask(@Param("id") Long id);

    QuartzTask getByDataId(@Param("dataId") Long dataId, @Param("status") TaskStatus status, @Param("group") TaskGroup group);

    QuartzTask getQuartzTask(Long dataId);

}
