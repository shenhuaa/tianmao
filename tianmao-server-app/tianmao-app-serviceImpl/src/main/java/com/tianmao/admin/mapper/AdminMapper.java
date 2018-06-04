package com.tianmao.admin.mapper;


import com.tianmao.model.user.Admin;
import com.tianmao.mybatis.BaseMapper;

import java.util.List;

public interface AdminMapper extends BaseMapper<Admin> {

    Admin getAdminByUsername(String username);

    List<Admin> getList(Admin admin);

    boolean updateAdminByAdminId(Admin admin);

    List<Admin> getAdminRoleById(Long userId);
}