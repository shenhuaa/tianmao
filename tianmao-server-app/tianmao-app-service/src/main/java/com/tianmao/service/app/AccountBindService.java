package com.tianmao.service.app;

import com.tianmao.service.model.user.AccountBind;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tianmao.service.type.user.AccountType;
@FeignClient(name = "${server.app.name}")
public interface AccountBindService {
    @RequestMapping(value = "/account/bind/create")
    void create(AccountBind accountBind);

    @RequestMapping(value = "/account/bind/getAccountBindByOpenId")
    AccountBind getAccountBindByOpenId(@RequestParam("openid")String openid, AccountType accountType);
}
