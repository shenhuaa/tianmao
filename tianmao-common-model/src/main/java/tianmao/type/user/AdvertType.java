package tianmao.type.user;

import tianmao.type.BaseEnum;

/**
 * 广告类型
 *
 * @author roach
 * @date 2017/11/23
 */
public enum AdvertType implements BaseEnum {

    LINK(0, "链接"),

    POST(1, "文章"),

    TALK(2, "微说"),

    TALK_VIDEO(3, "短视频"),

    CAR_CLUB(4, "社区"),

    CAR_CLUB_ACTIVITY(5, "社区活动"),

    CIRCLE_CAR(6, "社群"),

    CIRCLE_CAR_ACTIVITY(7, "社群活动"),

    AREA(8, "地区"),

    AREA_ACTIVITY(9, "地区活动"),

    USER(10, "用户"),

    BUSINESS(11, "商家"),

    GOODS(12, "商品"),

    IMAGE_TEXT(13, "图文");

    private int index;

    private String remark;

    AdvertType(int index, String remark) {
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
