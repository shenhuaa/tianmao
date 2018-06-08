
package com.tianmao.service.web.admin.shiro;

import com.tianmao.service.model.user.Permission;
import com.tianmao.service.admin.PermissionService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * web-admin Shiro配置
 *
 * @author roach
 * @date 2017/11/10
 */

@Configuration
public class AdminShiroConfiguration extends ShiroSupportConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(AdminShiroConfiguration.class);


    @Bean
    public ShiroFilterFactoryBean shiroFilter(PermissionService permissionService, DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = super.supportShiroFilterFactoryBean(securityManager);
        Map<String, Filter> filters = new LinkedHashMap<>();
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> filterChainDefinitionMap = shiroFilterFactoryBean.getFilterChainDefinitionMap();

        List<Permission> permissions = permissionService.getAllPermissions();
        for (Permission permission : permissions) {
            if (StringUtils.isEmpty(permission.getMark()) || StringUtils.isEmpty(permission.getLink())) {
                //filterChainDefinitionMap.put(permission.getUrl(), "anon");
                continue;
            }
            filterChainDefinitionMap.put(permission.getLink(), "user, perms[" + permission.getMark() + "]");
        }
        filterChainDefinitionMap.put("/**", "user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        return shiroFilterFactoryBean;
    }

    @Bean
    public Realm realm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        AdminShiroRealm realm = new AdminShiroRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher);
        realm.setCachingEnabled(false);
        return realm;
    }

}

