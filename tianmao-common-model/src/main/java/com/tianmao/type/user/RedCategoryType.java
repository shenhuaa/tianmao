package com.tianmao.type.user;


import com.tianmao.type.BaseEnum;

/**
 * 红包分类类型
 *
 * @author roach
 * @date 2017/12/7
 */
public enum RedCategoryType implements BaseEnum {

    REGISTER(0, "注册"),

    LOGIN(1, "登录"),

    POST(2, "发文章"),

    POST_SHARE(3, "文章分享"),

    TOPIC_SHARE(4, "话题分享"),

    CLUB_SHARE(5, "社区分享"),

    CAR_CIRCLE_SHARE(6, "社群分享"),

    CAR_CIRCLE_ACTIVITY_SHARE(7, "社群活动分享"),

    AREA_SHARE(8, "地区分享"),

    AREA_ACTIVITY_SHARE(9, "地区活动分享"),

    POST_REPLY(10, "文章回复评论"),

    INVITE_FRIENDS(11, "邀请好友"),

    JOIN_THE_CLUB(12, "加入社区"),

    FOLLOW(13, "关注"),

    ACTIVITY_ENROLL(14, "活动报名"),

    POST_REWARD(15, " 精华文章打赏"),

    RED_SHARE(16, " 红包分享"),

    TALK(17, "发微说"),

    VIDEO(18, "发短视频"),

    CLUB_ACTIVITY_SHARE(19, "社区活动分享"),

    ADVERTISING_LINK_JUMP(20, "广告链接跳转"),

    UPDATE_USER_INFO(21, "修改个人信息"),

    ACCOUNT_BIND(22, "账号绑定"),
    TALK_REPLY(23, "微说回复评论");

    private int index;

    private String remark;

    RedCategoryType(int index, String remark) {
        this.index = index;
        this.remark = remark;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}