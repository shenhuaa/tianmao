package com.tianmao.service.admin.controller;

import com.github.pagehelper.PageInfo;
import com.tianmao.api.admin.AdminClient;
import com.tianmao.service.admin.AdminService;
import com.tianmao.service.dto.user.AdminDtoFilter;
import com.tianmao.service.model.user.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController implements AdminClient {


    @Autowired
    private AdminService adminService;

    @Override
    public Admin save(@RequestBody Admin admin) {
        return adminService.save(admin);
    }

    @Override
    public Admin getAdminById(Long adminId) {
        return adminService.getAdminById(adminId);
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminService.getAdminByUsername(username);
    }

    @Override
    public boolean updatePassword(Long id, String newPassword) {
        return adminService.updatePassword(id,newPassword);
    }

    @Override
    public PageInfo<Admin> getPageInfo(@RequestBody AdminDtoFilter filter) {
        return adminService.getPageInfo(filter);
    }

    @Override
    public boolean updateStatus(Long id, Integer status) {
        return adminService.updateStatus(id,status);
    }

    @Override
    public boolean update(@RequestBody Admin admin) {
        return adminService.update(admin);
    }

    @Override
    public List<Admin> getAdminRoleById(Long userId) {
        return adminService.getAdminRoleById(userId);
    }
}
