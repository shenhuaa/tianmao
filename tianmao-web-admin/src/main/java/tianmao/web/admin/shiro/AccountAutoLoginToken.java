package tianmao.web.admin.shiro;
import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

/**
 * 账号自动登陆
 *
 * @author roach
 * @date 2017/12/21
 */
public class AccountAutoLoginToken implements HostAuthenticationToken, RememberMeAuthenticationToken {

    private Object principal;
    private String host;
    private boolean isRememberMe = true;

    public AccountAutoLoginToken(Long userId) {
        this.principal = userId;
    }

    public AccountAutoLoginToken(String principal) {
        this.principal = principal;
        this.isRememberMe = false;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return principal;
    }

    public void setPrincipal(Object principal) {
        this.principal = principal;
    }

    @Override
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public boolean isRememberMe() {
        return isRememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        isRememberMe = rememberMe;
    }

}
