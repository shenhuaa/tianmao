package com.tianmao.service.model.user;

import com.tianmao.service.type.user.UserStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 管理员实体类
 *
 * @author 陈明
 * @date 2017年09月01日
 */
@Data
@Entity
@Table(name = "admin")
public class Admin implements Serializable {
    private static final long serialVersionUID = -1552072156486197994L;

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private String username;

    private String password;

    private String passwordSalt;

    private String nickname;

    private String mobile;

    private String email;

    private Date addTime;

    private Date lastLoginTime;

    private UserStatus status;

    @Transient
    private List<Long> roleIds;

    @Transient
    private Long roleId;

    @Transient
    private String  roleName;

}