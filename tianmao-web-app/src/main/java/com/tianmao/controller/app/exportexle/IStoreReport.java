package com.tianmao.controller.app.exportexle;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 王瑞鸿
 * @Description:
 * @Date: Created in 11:40 2018/1/19
 */
@Entity
@Data
public class IStoreReport implements Serializable{

    private String head;

    private Double totalMoney;

    private Double totalOrder;

    private Long shopId;

    private Date beginTime;

    private Date endTime;

    private Long peopleNumber;

    private Long channelId;
}