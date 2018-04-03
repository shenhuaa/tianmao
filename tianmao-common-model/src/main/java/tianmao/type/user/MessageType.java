package tianmao.type.user;


import tianmao.type.BaseEnum;

/**
 * 消息类型
 *
 * @author roach
 * @date 2017/11/24
 */
public enum MessageType implements BaseEnum {

    //具体不清楚有什么类型
    PERSONAL(0, "个人消息"),

    SYSTEM(1, "系统消息");

    private int index;
    private String remark;

    MessageType(int index, String remark) {
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