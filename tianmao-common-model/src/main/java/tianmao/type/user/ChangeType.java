package tianmao.type.user;


import tianmao.type.BaseEnum;

/**
 * 金币、积分变更类型
 * <p>
 * Created by roach on 2016/11/27.
 */
public enum ChangeType implements BaseEnum {

    /**
     * 加
     */
    PLUS(0, "+"),

    /**
     * 减
     */
    MINUS(1, "-");


    ChangeType(int index, String remark) {
        this.index = index;
        this.remark = remark;
    }

    private int index;

    private String remark;


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
