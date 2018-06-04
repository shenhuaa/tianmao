package com.tianmao.app.mapper;

import com.tianmao.model.user.AccountBind;
import com.tianmao.mybatis.BaseMapper;
import com.tianmao.type.user.AccountType;
import org.apache.ibatis.annotations.Param;

public interface AccountBindMapper extends BaseMapper<AccountBind> {
    boolean create(AccountBind accountBind);
    /**
     * 根据openid获取账号绑定对象
     *
     * @param openid
     * @param accountType
     * @return
     */
    AccountBind getAccountBindByOpenId(@Param("openid") String openid, @Param("accountType") AccountType accountType);


    /**
     * 根据用户获取账号绑定对象
     * @param userId
     * @param accountType
     * @return
     */
    AccountBind getAccountBindByUserId(@Param("userId") Long userId, @Param("accountType") AccountType accountType);
}
