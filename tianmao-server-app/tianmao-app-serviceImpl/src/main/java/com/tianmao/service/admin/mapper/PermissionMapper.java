package com.tianmao.service.admin.mapper;

import com.tianmao.service.model.user.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by roach on 17/6/5.
 */
public interface PermissionMapper {

    boolean create(Permission permission);

    boolean update(Permission permission);

    List<Permission> getChildrenList(Long parentId);

    Permission getPermissionById(Long id);

    List<Permission> getPermissionsByIds(@Param("ids") Long[] ids);

    List<Permission> getAllPermissions();

    List<Long> getPermissionIds(@Param("roleId") Long roleId);

    boolean checkPermission(@Param("adminId") Long adminId, @Param("permission") String permission);

    List<Long> getListByMenuId(Long menuId);

    List<Permission> getListByParentId(@Param("parentId") Long parentId);

    List<Permission> getListByUserIdAndParentId(@Param("uid") Long uid, @Param("parentId") Long parentId);
}