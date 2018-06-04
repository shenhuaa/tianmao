package com.tianmao.web.admin.tags;

import com.tianmao.common.CurrentUserUtils;
import com.tianmao.model.user.Permission;
import com.tianmao.service.admin.PermissionService;
import freemarker.core.Environment;
import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 菜单标签
 * Created by roach on 17/6/5
 */
@Component
public class SidebarMenusTag implements TemplateDirectiveModel {

    private static final Logger logger = LoggerFactory.getLogger(SidebarMenusTag.class);
    public static Version version = Configuration.VERSION_2_3_23;

    @Autowired
    private PermissionService permissionService;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) {
        try {
            List<Permission> menus = permissionService.getListByUid(CurrentUserUtils.getCurrentUserId());
            env.setVariable("sidebarMenus", new DefaultObjectWrapperBuilder(version).build().wrap(menus));
            body.render(env.getOut());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}