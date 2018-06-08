package com.tianmao.service.model.sencode;

import com.tianmao.service.type.VerificationCodeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 发送验证码实体类
 *
 * @author roach
 * @date 2017/11/27
 */
@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "system_verification_code")
public class VerificationCode implements Serializable {

    private String id;

    /**
     * 账号
     */
    private String username;

    /**
     * 验证码
     */
    private int verificationCode;

    /**
     * 发送验证码时间间隔
     */
    private int intervalTime;

    /**
     * ip
     */
    private String ip;

    /**
     * 过期时间
     */
    private Date dueTime;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 发送类型
     */
    private VerificationCodeType codeType;

    /**
     * 发送状态
     */
    private Boolean status;

    /**
     * 是否删除
     */
    private Boolean deleted;


}