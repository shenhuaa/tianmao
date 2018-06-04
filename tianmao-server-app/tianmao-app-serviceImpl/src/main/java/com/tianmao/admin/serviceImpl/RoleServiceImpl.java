package com.tianmao.admin.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianmao.admin.mapper.RoleMapper;
import com.tianmao.common.HttpCode;
import com.tianmao.model.user.Permission;
import com.tianmao.model.user.Role;
import com.tianmao.service.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tianmao.admin.mapper.AdminWithRoleMapper;
import com.tianmao.admin.mapper.PermissionWithRoleMapper;
import com.tianmao.common.PagingAttribute;
import com.tianmao.dto.user.RoleDtoFilter;
import com.tianmao.service.exception.BaseServiceException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 角色服务
 *
 * @author roach
 * @date 2017/12/2
 */
@RestController
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionWithRoleMapper permissionWithRoleMapper;

    @Autowired
    private AdminWithRoleMapper adminWithRoleMapper;


    @Override
    public boolean update(@RequestBody Role role) {
        if (null == role || StringUtils.isEmpty(role.getName())) {
            throw new BaseServiceException(HttpCode.MISSING_PARAMETERS, "角色名不能为空");
        }
        if (roleMapper.checkRoleName(role.getId(), role.getName())) {
            throw new BaseServiceException(HttpCode.NAME_ALREADY_EXIST, "角色名称已经存在");
        }
        if (StringUtils.isEmpty(role.getId())) {
            // 创建角色
            role.setAddTime(new Date());
            roleMapper.create(role);
        } else {
            // 更新角色
            role.setUpdateTime(new Date());
            roleMapper.update(role);

            // 删除已关联的权限
            permissionWithRoleMapper.deleteByRoleId(role.getId());
        }

        //角色关联权限
        List<Permission> permissions = role.getPermissions();
        if (null != permissions && permissions.size() > 0) {
            boolean result = permissionWithRoleMapper.createByRoleId(role.getId(), permissions);
            Assert.isTrue(result, "角色关联权限失败");
        }
        return true;
    }

    @Override
    public PageInfo<Role> getPageInfo(@RequestBody RoleDtoFilter filter) {
        PagingAttribute page = filter.getPagingAttribute();
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        List<Role> roles = roleMapper.getList(filter);
        return new PageInfo<>(roles);
    }

    @Override
    public List<Role> getRoleList() {
        return roleMapper.getRoleList();
    }

    @Override
    public boolean delete(Long roleId) {
        boolean result = roleMapper.delete(roleId);
        if (result) {
            permissionWithRoleMapper.deleteByRoleId(roleId); // 删除关联权限
        }
        return result;
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleMapper.getRole(roleId);
    }

    @Override
    public List<Role> getRoleByAdminId(Long adminId) {
        List<Role> roles = adminWithRoleMapper.getRoleByAdminId(adminId);
        return null == roles ? new ArrayList<>() : roles;
    }

    @Override
    public List<Role> getRolesByIds(@RequestBody List<String> ids) {
        if (null == ids || 0 == ids.size()) {
            return new ArrayList<>();
        }
        return roleMapper.getRolesByIds(ids);
    }

    @Override
    public List<Long> getRoleIdsByAdminId(Long adminId) {
        return adminWithRoleMapper.getRoleIdsByAdminId(adminId);
    }
}