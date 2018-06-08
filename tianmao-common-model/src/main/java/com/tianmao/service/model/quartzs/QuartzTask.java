package com.tianmao.service.model.quartzs;

import com.tianmao.service.type.quartz.TaskGroup;
import com.tianmao.service.type.quartz.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 任务调度表
 *
 * @author roach
 * @date 2018/3/14
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "quartz_task")
public class QuartzTask implements Serializable {
    private static final long serialVersionUID = -5542963860208969957L;
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
    private Long userId;                                            //用户编号
    private Long dataId;                                            //编号
    private String taskName;                                        //任务名称
    private TaskGroup taskGroup;                                        //任务组
    private TaskStatus status;                                      //运行状态
    private String cronExpression;                                 //运行正则表达式
    private String module;                                          //模块名称
    private Integer priority;                                       //优先级
    private Date startTime;                                         //开始运行时间
    private Date createTime;
    private Date updateTime;
    @Transient
    private Map<String, Object> dataMap = new HashMap<>();

}
