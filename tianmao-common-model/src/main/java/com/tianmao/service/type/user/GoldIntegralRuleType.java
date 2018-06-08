package com.tianmao.service.type.user;


import com.tianmao.service.type.BaseEnum;

/**
 * 金币和积分规则
 * Created by roach on 2017/10/30.
 */
public enum GoldIntegralRuleType implements BaseEnum {

    SELECTION_ESSENCE(0, "评选精华"),
    SUBJECT_SELECTION(1, "评入选题"),
    USER_APPRECIATION_1(2, "用户打赏1元"),
    USER_APPRECIATION_5(3, "用户打赏5元"),
    USER_APPRECIATION_10(4, "用户打赏10元"),
    USER_APPRECIATION_50(5, "用户打赏50元"),
    POSTED(6, "发帖"),
    POST_BACK(7, "回贴"),
    SIGN_IN(8, "签到"),
    MICRO_SAID(9, "发微说"),
    PERFECT_PERSONAL_DATA(10, "完善个人资料"),
    REPORT_PASSED(11, "举报通过"),
    SHORT_VIDEO(12, "发短视频"),
    LIVE_BROADCAST(13, "发布直播"),
    HOMEPAGE(14, "设为首页"),
    RECOMMEND(15, "上推荐"),
    EXCHANGE_COMMODITY(16, "兑换商品"),
    COMMENT(17, "评论"),
    SPECIAL_OPERATIONS(18, "特殊操作"),
    SYSTEM_ADMINISTRATOR_OPERATION(19, "系统管理员操作"),
    LOGIN(20, "用户登陆"),
    APP_SHARE_RECOMMENDE_POST(21, "APP分享1篇推荐文章"),
    APP_SHARE_ESSENCE_POST(22, "APP分享1篇精华文章"),
    APP_SHARE_COMMON_POST(23, "APP分享普通文章"),
    APP_SHARE_SPECIAL_RECOMMENDE_POST(24, "APP分享特别推荐文章");

    private int index;

    private String remark;

    private ChangeType changeType;

    GoldIntegralRuleType(int index, String remark) {
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

    public ChangeType getChangeType() {
        return changeType;
    }

    public void setChangeType(ChangeType changeType) {
        this.changeType = changeType;
    }
}
