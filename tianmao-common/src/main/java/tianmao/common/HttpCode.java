package tianmao.common;

/**
 * 返回的状态码
 *
 * @author roach
 * @date 2017/12/8
 */
public enum HttpCode {

    OK(200, "成功"),
    NOT_FOUND(404, "路径不存在"),
    ERROR(500, "操作失败500"),

    MISSING_PARAMETERS(40001, "缺少参数"),
    ILLEGAL_PARAMETERS(40002, "非法的参数"),
    PARAMETER_TYPE_ERROR(40003, "参数类型错误"),
    TOKEN_EXPIRED(40004, "token已过期"),
    SIGN_ERROR(40005, "签名错误"),
    ADDRESS_EXPIRED(40006, "地址已过期"),
    ILLEGAL_REQUEST(40007, "非法的请求"),
    NOT_AUTHORIZED_TO_VISIT(40008, "您没有访问权限"),
    FILE_UPLOAD_EXCEEDS_MAXIMUM_LIMIT(40009, "文件上传超过最大限制"),
    VERIFICATION_CODE_ERROR(40010, "验证码错误"),
    VERIFICATION_CODE_EXPIRED(40011, "验证码已过期"),
    VERIFICATION_CODE_SENT_FREQUENTLY(40012, "验证码发送频繁"),
    USER_NOT_EXIST(40013, "用户不存在"),
    ACCOUNT_ALREADY_DISABLED(40014, "该账号已被禁用，请联系管理员"),
    ACCOUNT_ALREADY_FREEZE(40015, "账号已被冻结,请联系客服"),
    USERNAME_OR_PASSWORD_ERROR(40016, "用户名或密码错误"),
    MOBILE_ALREADY_REGISTERED(40017, "手机已注册"),
    MOBILE_NO_REGISTERED(40018, "手机未注册"),
    BALANCE_INSUFFICIENT(40019, "余额不足"),
    GOLD_INADEQUATE(40020, "金币不足"),
    REWARD_USER_NOT_EXIST(40021, "打赏用户不存在"),
    RED_CATEGORY_NOT_EXIST(40022, "红包分类不存在"),
    RED_FAILURE(40023, "红包已失效"),
    RED_EXPIRED(40024, "红包已过期"),
    RED_RECEIVED(40025, "红包已领取"),
    NAME_ALREADY_EXIST(40026, "名称已经存在"),
    ALREADY_APPLIED(40027, "已申请"),
    ALREADY_FOLLOW(40028, "已关注"),
    ALREADY_LIKED(40029, "已点赞"),
    WEEKLY_ONLY_DRAW_ONE(40030, "每周只能提现一次"),
    MOBILE_ALREADY_BIND(40031, "该手机已经被绑定过"),
    WEIXIN_ACCOUNT_ALREADY_BIND(40032, "微信账号已经绑定"),
    WEIXIN_ACCOUNT_NO_BIND(40033, "微信账号未绑定"),
    REMITTANCE_ACCOUNT_NO_BIND(40034, "支付宝汇款账号未绑定"),
    CHAR_ACCOUNT_ALREADY_REGISTERED(40035, "账号已经注册"),
    CHAR_ACCOUNT_NO_REGISTERED(40036, "账号未注册"),
    CHAR_REFRESH_TOKEN_FAIL(40037, "刷新token失败"),
    MOBILE_NO_REGISTERED_NORNAL(40038, "手机未注册"),
    MOBILE_ALREADY_REGISTERED_NORNAL(40039, "手机已注册"),
    GOOD_NO_PAY(40040, "商品未支付"),
    GOOD_ALREADY_PAY(40041, "商品已全部支付"),
    SHOP_NUMBER_NOT_EXIST(40042, "门店编号不存在"),
    GOOD_NOT_BELONG_TO_SHOP(40043, "商品不属于该门店"),
    SHOP_FORBIDDEN(40044, "门店已被禁用"),
    GOOD_INVALID(40045, "商品已失效"),
    VENDOR_PASS_NUMBER_NOT_EXIST(41042, "售货机货道编号不存在"),
    VENDOR_GOODS_OUT(41043, "售货机货道商品数量不足"),
    VENDOR_FORBIDDEN(41044, "售货机货道已被禁用"),
    VENDOR_ORDER_INVALID(41045, "订单已失效"),
    VENDOR_NETWORK_ANOMALY(41046, "售货机网络异常"),
    ORDER_IS_PAY(40100, "该订单已经支付了"),
    ORDER_EXCEPTION(40200, "支付异常了");


    private final int code;
    private final String message;

    HttpCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}