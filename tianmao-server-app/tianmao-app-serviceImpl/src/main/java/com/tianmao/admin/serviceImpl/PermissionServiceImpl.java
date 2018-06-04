package com.tianmao.admin.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tianmao.admin.mapper.PermissionMapper;
import com.tianmao.admin.mapper.PermissionWithRoleMapper;
import com.tianmao.model.user.Permission;
import com.tianmao.service.admin.PermissionService;

import java.util.List;
import java.util.Set;

/**
 * Created by roach on 17/6/5.
 */
@RestController
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private PermissionWithRoleMapper permissionWithRoleMapper;

    @Override
    public List<Permission> getListByUid(Long uid) {
        List<Permission> parentMenus = permissionMapper.getListByParentId(-1L);
        List<Permission> list = getChildrenByUserId(parentMenus, uid);
        for (int i = list.size() - 1; i >= 0; i--) {
            Permission permission = list.get(i);
            if (permission.getChildren().size() == 0 && !"/".equals(permission.getLink())) {
                list.remove(i);
            }
        }
        return list;
    }

    @Override
    public List<Long> getPermissionIdsByRoleId(Long roleId) {
        return null;
    }

    @Override
    public List<Permission> getList() {
        List<Permission> permissions = permissionMapper.getChildrenList(-1L);
        return getChildrenByUserId(permissions);
    }

    @Override
    public List<Permission> getListByRoleId(Long roleId) {
        return permissionWithRoleMapper.getListByRoleId(roleId);
    }

    @Override
    public Permission getPermissionById(Long id) {
        return permissionMapper.getPermissionById(id);
    }


    @Override
    public boolean save(@RequestBody Permission permission) {
        if (StringUtils.isEmpty(permission.getId())) {
            return permissionMapper.create(permission);
        }
        return permissionMapper.update(permission);
    }

    @Override
    public Set<String> getPermissionMarksByAdminId(Long adminId) {
        return permissionWithRoleMapper.getPermissionMarksByAdminId(adminId);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionMapper.getAllPermissions();
    }

    @Override
    public List<Long> getPermissionIds(Long roleId) {
        return permissionMapper.getPermissionIds(roleId);
    }

    @Override
    public boolean checkPermission(Long userId, String permission) {
        return permissionMapper.checkPermission(userId, permission);
    }

    @Override
    public List<Long> getListByMenuId(Long menuId) {
        return permissionMapper.getListByMenuId(menuId);
    }

    private List<Permission> getChildrenByUserId(List<Permission> permissions) {
        for (Permission permission : permissions) {
            List<Permission> children = permissionMapper.getChildrenList(permission.getId());
            if (children.size() > 0) {
                permission.setChildren(children);
                getChildrenByUserId(children);
            }
            permission.setChildren(children);
        }
        return permissions;
    }

    private List<Permission> getChildrenByUserId(List<Permission> parentPermissions, Long uid) {
        for (Permission permission : parentPermissions) {
            List<Permission> children = permissionMapper.getListByUserIdAndParentId(uid, permission.getId());
            if (children.size() > 0) {
                permission.setChildren(children);
                getChildrenByUserId(children, uid);
            }
            permission.setChildren(children);
        }
        return parentPermissions;
    }
}