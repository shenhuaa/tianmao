package com.tianmao.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.tianmao.type.user.AccountType;
import com.tianmao.type.user.BindType;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 第三方账号绑定
 *
 * @author roach
 * @date 2017/11/28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_account_bind")
public class AccountBind implements Serializable {

    private static final long serialVersionUID = 7246504049522262052L;
    private String id;
    private Long userId;
    private String openid;
    private AccountType accountType;
    private BindType bindType;
    private Date bindTime;
    private Boolean deleted;

}
