package tianmao.type.user;

import tianmao.type.BaseEnum;

/**
 * 账号登陆类型
 *
 * @author roach
 * @date 2017/11/28
 */
public enum BindType implements BaseEnum {

    ACCOUNT_BIND(0, "账号绑定"),

    ACCOUNT_REGISTER_BIND(1, "账号注册并绑定");

    private int index;

    private String remark;

    BindType(int index, String remark) {
        this.index = index;
        this.remark = remark;
    }

    /**
     * 根据索引获取对象
     *
     * @param ordinal
     * @return
     */
    public static BindType valueOf(int ordinal) {
        if (ordinal < 0 || ordinal >= values().length) {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return values()[ordinal];
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
        return "BindType{" +
                "index=" + index +
                ", remark='" + remark + '\'' +
                '}';
    }
}
