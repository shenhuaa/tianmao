package tianmao.type.user;
import tianmao.type.BaseEnum;

/**
 * 消息模板类型
 *
 * @author roach
 * @date 2017/11/23
 */
public enum MessageTemplateType implements BaseEnum {

    POST_SET_ESSENCE(0, "文章被设精华"),

    RECOMMENDED_HOME(1, "文章推荐上首页"),

    COMMUNITY_WAITING(2, "社区等待审核"),

    QUESTION_POST(3, "文章审核情况"),

    APPLY_COMMUNITY_AUDIT_PASSED(4, "申请社区已通过审核"),

    APPLY_COMMUNITY_AUDIT_FAILURE(5, "申请社区未通过审核"),

    USER_ADD_COMMUNITY(6, "用户加入社区"),

    SYSTEM_PUSH(7, "系统推送"),

    TALK_SET_ESSENCE(8, "微说被设精华"),

    USER_WITHDRAW_MONEY(9, "用户提现失败"),

    POST_RECEIVE_COMMENT(10, "收到的文章评论"),

    FEEDBACK(11, "意见反馈"),

    TALK_RECEIVE_COMMENT(12, "收到的微说评论"),

    TRANSFERRED_ACCOUNT(13, "提现成功");

    private int index;

    private String remark;

    MessageTemplateType(int index, String remark) {
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