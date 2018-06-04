package com.tianmao.model.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.tianmao.type.user.IdentityType;
import com.tianmao.type.user.Sex;
import com.tianmao.type.user.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用户实体类
 *
 * @author 陈明
 * @date 2017年09月01日
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_info")
public class User implements Serializable {
    private static final long serialVersionUID = -1552072156486197994L;
    @Id
    private Long id;
    @GeneratedValue(generator = "JDBC")
    private String nickname;                        //昵称
    private String mobile;                          //手机
    @JsonView
    private String password;                        //用户密码
    private String passwordSalt;                   //密码盐
    private String realName;                        //真实姓名
    private String province;                        //省份
    private String city;                             //城市
    private String headImgUrl;                      //用户头像
    private String userNumber;                      //会员卡号
    private IdentityType identityType;              //身份类型
    private String signature;                       //个人签名
    private String alipay;                          //阿里账号
    private BigDecimal balance;                     //余额
    private Integer ratio;                          //红包比例
    private String birthDate;                      //出生日期
    private Date lastLoginTime;                    //最后登陆时间
    private Date regTime;                          //注册时间
    private Sex sex;                                //性别类型
    private UserStatus status;                     //用户状态

    @Transient
    private Integer rewardNum;                    //被打赏数

    @Transient
    private BigDecimal rewardAmount;             //打赏所得

    @Transient
    private Integer redNum;                      //获得红包

    @Transient
    private BigDecimal redAmount;               //红包所得

    @Transient
    private Integer myFans;                     //我的粉丝数

    @Transient
    private Integer myAttentions;              //我的关注数

    @Transient
    private AccountBind accountBind;

    @Transient
    private Integer postNum;                   //帖子数
    @Transient
    private Integer talkNum;                   //微说数

    @Transient
    private Boolean mutualConcern;            //是否相互关注

    @Transient
    private Integer integral;            //积分

    @Transient
    private Date startTime;

    @Transient
    private Integer clubLevel;                 //用户在车友会的级别

    @Transient
    private Date endTime;



    @Transient
    private Long activityId;  //活动表id

    @Transient
    private String postTitle;  //帖子标题

    @Transient
    private Long blackId;    //黑名单id

    @Transient
    private Boolean heAttention;            //关注状态类型

    @Transient
    private String openId;


    @Transient
    private List<Long> releaseIdList;               //作者id集合

    @Transient
    private Boolean  isReleaseIdList;               //判断是否有作者id集合

    @Transient
    private Long  heId;               //对方id

}