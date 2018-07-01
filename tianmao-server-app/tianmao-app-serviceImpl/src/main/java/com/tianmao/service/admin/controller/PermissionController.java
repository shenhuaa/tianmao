package com.tianmao.service.admin.controller;
import com.tianmao.api.admin.PermissionClient;
import com.tianmao.service.admin.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;


@RestController
public class PermissionController implements PermissionClient {

    @Autowired
    private PermissionService permissionService;
    @Override
    public List<com.tianmao.service.model.user.Permission> getListByUid(Long uid) {
        return permissionService.getListByUid(uid);
    }

    @Override
    public List<Long> getPermissionIdsByRoleId(Long roleId) {
        return permissionService.getPermissionIdsByRoleId(roleId);
    }

    @Override
    public List<com.tianmao.service.model.user.Permission> getList() {
        return permissionService.getList();
    }

    @Override
    public List<com.tianmao.service.model.user.Permission> getListByRoleId(Long roleId) {
        return permissionService.getListByRoleId(roleId);
    }

    @Override
    public com.tianmao.service.model.user.Permission getPermissionById(Long id) {
        return null;
    }

    @Override
    public boolean save(com.tianmao.service.model.user.Permission permission) {
        return false;
    }

    @Override
    public Set<String> getPermissionMarksByAdminId(Long adminId) {
        return null;
    }

    @Override
    public List<com.tianmao.service.model.user.Permission> getAllPermissions() {
        return null;
    }

    @Override
    public List<Long> getPermissionIds(Long roleId) {
        return null;
    }

    @Override
    public boolean checkPermission(Long adminId, String permission) {
        return false;
    }

    @Override
    public List<Long> getListByMenuId(Long menuId) {
        return null;
    }
}