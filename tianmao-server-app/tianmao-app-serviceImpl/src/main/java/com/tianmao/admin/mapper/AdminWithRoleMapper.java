package com.tianmao.admin.mapper;

import com.tianmao.model.user.Role;
import org.apache.ibatis.annotations.Param;

import java.awt.print.Pageable;
import java.util.List;

/**
 * 账户和角色关联
 * <p/>
 * Created by roach on 17/6/5.
 */
public interface AdminWithRoleMapper {

    List<Role> getRoleByAdminId(@Param("adminId") Long adminId);

    boolean addAdminRoles(@Param("adminId") Long adminId,
                          @Param("roles") List<Long> roles);

    boolean deleteByAdminId(Long adminId);

    boolean deleteAdminRoles(@Param("adminId") Long adminId,
                             @Param("rolesId") List<Long> rolesId);

    List<String> getByMark(@Param("marks") List<String> marks,
                           @Param("pageable") Pageable pageable);

    int getTotal(@Param("marks") List<String> marks);

    boolean createWithRole(@Param("adminId") String adminId, @Param("roles") List<String> roles);

    boolean hasRoleByAdminId(@Param("adminId") Long adminId, @Param("roleId") Long roleId);

    List<Long> getRoleIdsByAdminId(Long adminId);

    boolean updateAdminWithRole(@Param("roleId") Long roleId, @Param("adminId") Long adminId);

    boolean insertAdminWithRole(@Param("roleId") Long roleId, @Param("adminId") Long adminId);
}