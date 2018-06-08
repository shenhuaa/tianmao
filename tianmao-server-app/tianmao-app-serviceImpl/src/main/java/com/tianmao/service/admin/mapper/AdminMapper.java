package com.tianmao.service.admin.mapper;


import com.tianmao.service.model.user.Admin;
import com.tianmao.service.mybatis.BaseMapper;

import java.util.List;

public interface AdminMapper extends BaseMapper<Admin> {

    Admin getAdminByUsername(String username);

    List<Admin> getList(Admin admin);

    boolean updateAdminByAdminId(Admin admin);

    List<Admin> getAdminRoleById(Long userId);
}