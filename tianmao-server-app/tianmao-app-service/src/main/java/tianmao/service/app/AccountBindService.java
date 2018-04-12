package tianmao.service.app;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tianmao.model.user.AccountBind;
import tianmao.type.user.AccountType;
@FeignClient(name = "${server.app.name}")
public interface AccountBindService {
    @RequestMapping(value = "/account/bind/create")
    void create(AccountBind accountBind);

    @RequestMapping(value = "/account/bind/getAccountBindByOpenId")
    AccountBind getAccountBindByOpenId(@RequestParam("openid")String openid, AccountType accountType);
}
