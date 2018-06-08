package com.tianmao.service.model.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 角色表
 *
 * @author roach
 * @date 2017/12/2
 */
@Data
@Entity
@Table(name = "admin_role")
public class Role implements Serializable {

    private static final long serialVersionUID = -4771968020294703892L;
    private Long id;
    private String name;
    private String remark;
    private Date addTime;
    private Date updateTime;
    private List<Permission> permissions = new ArrayList<>();

}