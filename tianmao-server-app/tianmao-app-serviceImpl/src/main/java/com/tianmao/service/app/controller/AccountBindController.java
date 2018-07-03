package com.tianmao.service.app.controller;

import com.tianmao.api.app.AccountBindClient;
import com.tianmao.service.app.AccountBindService;
import com.tianmao.service.model.user.AccountBind;
import com.tianmao.service.type.user.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountBindController implements AccountBindClient {


    @Autowired
    private  AccountBindService accountBindService;

    @Override
    public void create(@RequestBody AccountBind accountBind) {
        accountBindService.create(accountBind);
    }

    @Override
    public AccountBind getAccountBindByOpenId(String openid, @RequestBody AccountType accountType) {
        return accountBindService.getAccountBindByOpenId(openid,accountType);
    }
}
