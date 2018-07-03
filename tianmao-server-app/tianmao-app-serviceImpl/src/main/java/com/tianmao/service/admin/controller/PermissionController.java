package com.tianmao.service.admin.controller;
import com.tianmao.api.admin.PermissionClient;
import com.tianmao.service.admin.PermissionService;
import com.tianmao.service.model.user.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;


@RestController
public class PermissionController implements PermissionClient {

    @Autowired
    private PermissionService permissionService;
    @Override
    public List<Permission> getListByUid(Long uid) {
        return permissionService.getListByUid(uid);
    }

    @Override
    public List<Long> getPermissionIdsByRoleId(Long roleId) {
        return permissionService.getPermissionIdsByRoleId(roleId);
    }

    @Override
    public List<Permission> getList() {
        return permissionService.getList();
    }

    @Override
    public List<Permission> getListByRoleId(Long roleId) {
        return permissionService.getListByRoleId(roleId);
    }

    @Override
    public com.tianmao.service.model.user.Permission getPermissionById(Long id) {
        return permissionService.getPermissionById(id);
    }

    @Override
    public boolean save(@RequestBody Permission permission) {
        return permissionService.save(permission);
    }

    @Override
    public Set<String> getPermissionMarksByAdminId(Long adminId) {
        return permissionService.getPermissionMarksByAdminId(adminId);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    @Override
    public List<Long> getPermissionIds(Long roleId) {
        return permissionService.getPermissionIds(roleId);
    }

    @Override
    public boolean checkPermission(Long adminId, String permission) {
        return permissionService.checkPermission(adminId,permission);
    }

    @Override
    public List<Long> getListByMenuId(Long menuId) {
        return permissionService.getListByMenuId(menuId);
    }
}