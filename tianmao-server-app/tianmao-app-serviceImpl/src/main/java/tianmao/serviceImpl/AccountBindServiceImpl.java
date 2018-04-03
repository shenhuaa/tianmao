package tianmao.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tianmao.common.HttpCode;
import tianmao.mapper.AccountBindMapper;
import tianmao.model.user.AccountBind;
import tianmao.service.AccountBindService;
import tianmao.service.exception.BaseServiceException;
import tianmao.type.user.AccountType;
@RestController
public class AccountBindServiceImpl implements AccountBindService {
    @Autowired
    private AccountBindMapper accountBindMapper;
    @Override
    public void create( @RequestBody AccountBind accountBind) {
        AccountBind dbAccountBind = accountBindMapper.getAccountBindByOpenId(accountBind.getOpenid(), accountBind.getAccountType());
        if (dbAccountBind != null) {
            throw new BaseServiceException(HttpCode.WEIXIN_ACCOUNT_ALREADY_BIND, "微信账号已经绑定");
        }
        dbAccountBind = accountBindMapper.getAccountBindByUserId(accountBind.getUserId(), accountBind.getAccountType());
        if (dbAccountBind != null) {
            throw new BaseServiceException(HttpCode.MOBILE_ALREADY_BIND, "该手机已经被绑定过");
        }
         accountBindMapper.create(accountBind);
    }

    @Override
    public AccountBind getAccountBindByOpenId(String openid,  @RequestBody AccountType accountType) {
        return accountBindMapper.getAccountBindByOpenId(openid, accountType);
    }
}
