package com.tianmao.service.admin.mapper;

import com.tianmao.service.model.user.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by roach on 17/6/5.
 */
public interface PermissionWithRoleMapper {

    List<Permission> getListByAdminId(Long adminId);

    List<Permission> getListByRoleId(Long roleId);

    boolean createByRoleId(@Param("roleId") Long roleId, @Param("permissions") List<Permission> permissions);

    boolean deleteByRoleId(Long roleId);

    Set<String> getPermissionMarksByAdminId(Long adminId);

}