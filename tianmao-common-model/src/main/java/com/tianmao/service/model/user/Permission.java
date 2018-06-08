package com.tianmao.service.model.user;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限
 * Created by roach on 2017/6/3.
 */
@Data
public final class Permission implements Serializable {

    private static final long serialVersionUID = 764899429482858587L;
    private Long id;
    private Long parentId;
    private String link;
    private String icon;
    private Integer sort;
    private String name;
    private String mark;
    private Permission parent;
    private List<Permission> children = new ArrayList<>();

}