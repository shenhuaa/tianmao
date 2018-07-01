package com.tianmao.service.admin.controller;


import com.github.pagehelper.PageInfo;
import com.tianmao.api.admin.RoleClient;
import com.tianmao.service.admin.RoleService;
import com.tianmao.service.dto.user.RoleDtoFilter;
import com.tianmao.service.model.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController implements RoleClient {

    @Autowired
    private RoleService roleService;

    @Override
    public boolean update(@RequestBody Role role) {
        return roleService.update(role);
    }

    @Override
    public boolean delete(Long roleId) {
        return roleService.delete(roleId);
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleService.getRoleById(roleId);
    }

    @Override
    public List<Role> getRoleByAdminId(Long adminId) {
        return roleService.getRoleByAdminId(adminId);
    }

    @Override
    public List<Role> getRolesByIds(@RequestBody List<String> ids) {
        return roleService.getRolesByIds(ids);
    }

    @Override
    public List<Long> getRoleIdsByAdminId(Long adminId) {
        return null;
    }

    @Override
    public PageInfo<Role> getPageInfo(RoleDtoFilter filter) {
        return null;
    }

    @Override
    public List<Role> getRoleList() {
        return null;
    }
}