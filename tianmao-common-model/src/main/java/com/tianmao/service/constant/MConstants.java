package com.tianmao.service.constant;

/**
 * 常量
 *
 * @author 陈明
 * @date 2017年09月01日
 */
public class MConstants {
    /******************** 公共 *********************/
    // 登录页
    public static final String LOGIN_PAGE = "login";
    // 跳转登录页
    public static final String LOGIN = "redirect:/login";
    // 首页
    public static final String PAGE_HOME = "/index";
    // 跳转首页
    public static final String REDIRECT_PAGE_HOME = "redirect:/index";
    // 无权限
    public static final String REDIRECT_AUTHORITY_NO = "/noAuthority";
    // 没有权限页
    public static final String AUTHORITY_NO = "/403";
    //静态资源文件默认路径
    public static final String UPLOAD_DEFALUT = "upload/";


    /********************* 工作台 *********************/
    // 欢迎页
    public static final String CONSOLE_INDEX = "/console/index";
    // 跳转欢迎页
    public static final String REDIRECT_CONSOLE_LIST = "redirect:/console/index";
    // 修改密码列表
    public static final String RESETPASSWORD = "/console/reset_password";
    // 修改个人信息
    public static final String RESETADMIN = "/console/reset_admin";


    /************************************************ 红包管理************************************************/
    // 红包列表
    public static final String RED_LIST = "red/red-list";
    // 红包添加
    public static final String RED_ADD = "red/red-add";
    // 红包编辑
    public static final String RED_EDIT = "red/red-edit";
    // 跳转红包列表
    public static final String REDIRECT_RED_LIST = "redirect:/red/list";

    // 红包分类列表
    public static final String RED_CATEGORY_LIST = "red/category-list";
    // 红包分类添加
    public static final String RED_CATEGORY_ADD = "red/category-add";
    // 红包分类编辑
    public static final String RED_CATEGORY_EDIT = "red/category-edit";
    // 跳转红包分类列表
    public static final String REDIRECT_RED_CATEGORY_LIST = "redirect:/red/category/list";

    // 活动列表
    public static final String ACTIVITY_LIST = "red/activity-list";
    // 活动添加
    public static final String ACTIVITY_ADD = "red/activity-add";
    // 活动编辑
    public static final String ACTIVITY_EDIT = "red/activity-edit";
    // 跳转活动列表
    public static final String REDIRECT_ACTIVITY_LIST = "redirect:/red/activity/list";


    /************************************************ 用户管理************************************************/
    // 用户列表
    public static final String USER_LIST = "/user/user-list";
    // 用户添加
    public static final String USER_ADD = "/user/user-add";
    // 用户编辑
    public static final String USER_EDIT = "/user/user-edit";
    // 用户红包比例编辑
    public static final String USER_RATIO_EDIT = "/user/user-ratio-edit";
    // 跳转用户列表
    public static final String REDIRECT_USER_LIST = "redirect:/user/list";

    // 用户提现列表
    public static final String USER_DRAW_LIST = "/user/draw-list";
    // 用户提现添加
    public static final String USER_DRAW_ADD = "/user/draw-add";
    // 用户提现编辑
    public static final String USER_DRAW_EDIT = "/user/draw-edit";
    // 跳转用户提现列表
    public static final String REDIRECT_USER_DRAW_LIST = "redirect:/user/draw/list";

    // 用户红包列表
    public static final String USER_RED_LIST = "/user/red-list";
    // 用户红包添加
    public static final String USER_RED_ADD = "/user/red-add";
    // 用户红包编辑
    public static final String USER_RED_EDIT = "/user/red_-edit";
    // 跳转用户红包列表
    public static final String REDIRECT_USER_RED_LIST = "redirect:/user/red/list";

    // 用户操作记录列表
    public static final String USER_OPERATE_LOG_LIST = "/user/operate-list";
    // 用户操作记录添加
    public static final String USER_OPERATE_LOG_ADD = "/user/operate-add";
    // 用户操作记录编辑
    public static final String USER_OPERATE_LOG_EDIT = "/user/operate-edit";
    // 跳转用户操作记录列表
    public static final String REDIRECT_USER_OPERATE_LOG_LIST = "redirect:/user/operate/list";

    // 用户打赏列表
    public static final String USER_REWARD_LIST = "/user/reward-list";
    // 用户打赏添加
    public static final String USER_REWARD_ADD = "/user/reward-add";
    // 用户打赏编辑
    public static final String USER_REWARD_EDIT = "/user/reward-edit";
    // 跳转用户打赏列表
    public static final String REDIRECT_USER_REWARD_LIST = "redirect:/user/reward/list";

    /************************************************ 统计报表************************************************/
    // 红包报表列表
    public static final String REPORT_RED_LIST = "/report/red-list";
    // 用户报表列表
    public static final String REPORT_USER_LIST = "/report/user-list";
    // 打赏报表列表
    public static final String REPORT_REWARD_LIST = "/report/reward-list";
    // 用户打赏报表列表
    public static final String REPORT_REWARD_USER_LIST = "/report/reward-user-list";
    /********istore**********/
    // 销售报表
    public static final String REPORT_SALE = "/report/sale";

    //进店人数高峰期报表
    public static final String  ENTER_SHOP= "/report/enter-shop";

    /************************************************ 系统 设置************************************************/
    // 管理员列表
    public static final String ADMIN_LIST = "/system/admin_list";
    // 管理员添加
    public static final String ADMIN_ADD = "/system/admin_add";
    // 管理员编辑
    public static final String ADMIN_EDIT = "/system/admin_edit";
    // 跳转管理员列表
    public static final String REDIRECT_ADMIN_LIST = "redirect:/system/admin/list";


    // 角色列表
    public static final String ROLE_LIST = "/system/role_list";
    // 角色添加
    public static final String ROLE_ADD = "/system/role_add";
    // 角色编辑
    public static final String ROLE_EDIT = "/system/role_edit";
    // 跳转角色列表
    public static final String REDIRECT_ROLE_LIST = "redirect:/system/role/list";

    // 消息列表
    public static final String MESSAGE_LIST = "/system/message-list";
    // 消息添加
    public static final String MESSAGE_ADD = "/system/message-add";
    // 消息编辑
    public static final String MESSAGE_EDIT = "/system/message-edit";
    // 跳转消息列表
    public static final String REDIRECT_MESSAGE_LIST = "redirect:/system/message/list";

    /************************************************ 平台配置************************************************/
    //兴趣圈列表
    public static final String CIRCLE_INTEREST_LIST = "/configure/circle_interest_list";
    //添加兴趣圈
    public static final String CIRCLE_INTEREST_ADD = "/configure/circle_interest_add";
    //编辑兴趣圈
    public static final String CIRCLE_INTEREST_UPDATE = "/configure/circle_interest_update";
    //搜索热词列表
    public static final String SEARCH_HOT_WORD_LIST = "/configure/search_hot_word_list";
    //添加搜索热词
    public static final String SEARCH_HOT_WORD_ADD = "/configure/search_hot_word_add";
    //修改搜索热词
    public static final String SEARCH_HOT_WORD_UPDATE = "/configure/search_hot_word_update";
    //金币积分列表
    public static final String GOLD_INTEGRAL_RULE_LIST = "/configure/gold_integral_rule_list";
    //修改金币积分
    public static final String GOLD_INTEGRAL_RULE_UPDATE = "/configure/gold_integral_rule_update";
    //广告图列表
    public static final String ADVERT_LIST = "/configure/advert_list";
    //修改广告图
    public static final String ADVERT_UPDATE = "/configure/advert_update";
    //敏感词列表
    public static final String SENSITIVE_WORD_LIST = "/configure/sensitive_word_list";
    //添加敏感词
    public static final String SENSITIVE_WORD_ADD = "/configure/sensitive_word_add";
    //修改敏感词
    public static final String SENSITIVE_WORD_UPDATE = "/configure/sensitive_word_update";
    //开关审核列表
    public static final String AUDIT_SWITCH_LIST = "/configure/audit_switch_list";

    /************************************************ 平台待办管理************************************************/
    //车友会审核列表
    public static final String AUDIT_CAR_CLUB_LIST = "/remain/audit_car_club_list";
    //用户举报列表
    public static final String REPORT_LIST = "/remain/report_list";
    //更新用户举报
    public static final String REPORT_UPDATE = "/remain/report_update";
    //意见反馈列表
    public static final String FEEDBACK_LIST = "/remain/feedback_list";
    //意见反馈处理页面
    public static final String FEEDBACK_UPDATE = "/remain/feedback_update";

    /************************************************ 帖子管理************************************************/
    //帖子列表
    public static final String POST_LIST = "/post/post_list";
    //帖子审核页面
    public static final String POST_AUDIT = "/post/post_audit";
    //帖子评论列表
    public static final String COMMENT_LIST = "/post/comment_list";
    //微说列表
    public static final String TALK_LIST = "/post/talk_list";
    //微说审核页面
    public static final String TALK_AUDIT = "/post/talk_audit";
    //微说评论列表
    public static final String TALK_COMMENT_LIST = "/post/talk_comment_list";

    //车友会列表
    public static final String CAR_CLUB_LIST = "/circle/car_club_list";
    //添加车友会
    public static final String CAR_CLUB_ADD = "/circle/car_club_add";
    //修改车友会
    public static final String CAR_CLUB_UPDATE = "/circle/car_club_update";
    //车友会成员列表
    public static final String CAR_CLUB_MEMBER_LIST = "/circle/car_club_member_list";
    //车友会活动列表
    public static final String CIRCLE_ACTIVITY_LIST = "/circle/circle_activity_list";
    //车友会帖子列表
    public static final String CAR_CLUB_POST_LIST = "/circle/car_club_post_list";
    //城市圈列表
    public static final String CIRCLE_CITY_LIST = "/circle/city_list";
    //城市圈修改
    public static final String CIRCLE_CITY_UPDATE = "/circle/city_update";
    //车系圈列表
    public static final String CIRCLE_CAR_LIST = "/circle/car_list";
    //车系圈修改
    public static final String CIRCLE_CAR_UPDATE = "/circle/car_update";

    public class Parameter {
        // 当前用户
        public static final String CURRENT_USER = "current_user";
        // 验证码
        public static final String VALIDATE_CODE = "validateCode";
        // 实体
        public static final String MODEL = "model";
    }

}
