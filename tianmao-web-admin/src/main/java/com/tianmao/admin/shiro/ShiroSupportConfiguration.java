package com.tianmao.admin.shiro;

import com.tianmao.utils.Sha512HashPasswordUtil;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 父类Shiro配置
 *
 * @author roach
 * @date 2017/11/10
 */
@Configuration
public class ShiroSupportConfiguration {

    /**
     * 公用的配置
     *
     * @param securityManager
     * @return
     */
    public ShiroFilterFactoryBean supportShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //静态资源
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/robots.txt", "anon");

        //请求url
        filterChainDefinitionMap.put("/login/**", "anon");
        filterChainDefinitionMap.put("/mobile/login/**", "anon");
        filterChainDefinitionMap.put("/weixin/**", "anon");
        filterChainDefinitionMap.put("/logout/**", "logout");
        filterChainDefinitionMap.put("/403/**", "anon");
        filterChainDefinitionMap.put("/404/**", "anon");
        filterChainDefinitionMap.put("/405/**", "anon");
        filterChainDefinitionMap.put("/500/**", "anon");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        return shiroFilterFactoryBean;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorization = new AuthorizationAttributeSourceAdvisor();
        authorization.setSecurityManager(securityManager);
        return authorization;
    }

    @Bean
    public ServletContainerSessionManager servletContainerSessionManager() {
        return new ServletContainerSessionManager();
    }

    @Bean
    public DefaultWebSecurityManager securityManager(Realm realm, AccountAutoLoginRealm accountAutoLoginRealm, ServletContainerSessionManager servletContainerSessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(servletContainerSessionManager);
        securityManager.setAuthenticator(new ModularRealmAuthenticator());
        List<Realm> realms = new ArrayList<>();
        realms.add(realm);
        realms.add(accountAutoLoginRealm);
        securityManager.setRealms(realms);
        return securityManager;
    }

    /**
     * 自动登陆
     *
     * @return
     */
    @Bean
    public AccountAutoLoginRealm accountAutoLoginRealm() {
        AccountAutoLoginRealm realm = new AccountAutoLoginRealm();
        realm.setAuthenticationTokenClass(AccountAutoLoginToken.class);
        realm.setCachingEnabled(false);
        return realm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashIterations(Sha512HashPasswordUtil.HAS_HITERATIONS);
        hashedCredentialsMatcher.setHashAlgorithmName(Sha512HashPasswordUtil.HASH_ALGORITHM_NAME);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(false);
        return hashedCredentialsMatcher;
    }


}


