package tianmao.web.admin.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * 重写异常返回
 *
 * @author roach
 * @date 2017/12/21
 */
public class ModularRealmAuthenticator extends org.apache.shiro.authc.pam.ModularRealmAuthenticator {

    private static final Logger log = LoggerFactory.getLogger(ModularRealmAuthenticator.class);

    protected AuthenticationInfo doMultiRealmAuthentication(Collection<Realm> realms, AuthenticationToken token) {

        AuthenticationStrategy strategy = getAuthenticationStrategy();

        AuthenticationInfo aggregate = strategy.beforeAllAttempts(realms, token);

        if (log.isTraceEnabled()) {
            log.trace("Iterating through {} realms for PAM authentication", realms.size());
        }

        for (Realm realm : realms) {

            aggregate = strategy.beforeAttempt(realm, token, aggregate);

            if (realm.supports(token)) {

                log.trace("Attempting to authenticate token [{}] using realm [{}]", token, realm);

                AuthenticationInfo info = null;
                Throwable t = null;
                try {
                    info = realm.getAuthenticationInfo(token);
                } catch (Throwable throwable) {
                    t = throwable;

                    //重写异常返回======================================================================================
                    if (throwable instanceof IncorrectCredentialsException) {
                        throw new IncorrectCredentialsException();
                    } else if (throwable instanceof UnknownAccountException) {
                        throw new UnknownAccountException();
                    } else if (throwable instanceof DisabledAccountException) {
                        throw new DisabledAccountException();
                    }
                    //重写异常返回======================================================================================

                    if (log.isDebugEnabled()) {
                        String msg = "Realm [" + realm + "] threw an exception during a multi-realm authentication attempt:";
                        log.debug(msg, t);
                    }
                }

                aggregate = strategy.afterAttempt(realm, token, info, aggregate, t);

            } else {
                log.debug("Realm [{}] does not support token {}.  Skipping realm.", realm, token);
            }
        }

        aggregate = strategy.afterAllAttempts(token, aggregate);

        return aggregate;
    }

}
