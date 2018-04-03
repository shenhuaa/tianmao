package tianmao.type.user;


import tianmao.type.BaseEnum;

/**
 * 启用状态
 *
 * @author roach
 * @date 2017/11/23
 */
public enum Status implements BaseEnum {

    ENABLE(0, "启用"),

    DISABLE(1, "禁用");

    private int index;
    private String remark;

    Status(int index, String remark) {
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

    @Override
    public String toString() {
        return "Status{" +
                "index=" + index +
                ", remark='" + remark + '\'' +
                '}';
    }
}
