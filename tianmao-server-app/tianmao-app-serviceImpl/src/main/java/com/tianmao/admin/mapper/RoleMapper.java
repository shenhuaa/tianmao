package com.tianmao.admin.mapper;

import com.tianmao.model.user.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by roach on 17/6/5.
 */
public interface RoleMapper {

    boolean create(Role role);

    boolean update(Role role);

    boolean delete(Long roleId);

    List<Role> getList(Role role);

    Role getRole(Long roleId);

    boolean checkRoleName(@Param("roleId") Long roleId, @Param("name") String name);

    List<Role> getRolesByIds(List<String> ids);

    List<Role> getRoleList();
}