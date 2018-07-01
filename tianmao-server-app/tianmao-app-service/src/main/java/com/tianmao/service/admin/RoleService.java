package com.tianmao.service.admin;

import com.github.pagehelper.PageInfo;
import com.tianmao.service.dto.user.RoleDtoFilter;
import com.tianmao.service.model.user.Role;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 角色服务
 * @author roach
 * @date 2017/12/2
 */
public interface RoleService {

    /**
     * 更新和创建
     *
     * @param role 如果角色ID为空将创建，否则更新。
     * @return 创建成功后的ID，失败则为空字符串
     */
    boolean update(Role role) ;

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    boolean delete(@RequestParam("roleId") Long roleId);

    /**
     * 获得单个角色
     *
     * @param roleId
     * @return
     */
    Role getRoleById(@RequestParam("roleId") Long roleId);

    /**
     * 获取用户对应的角色
     *
     * @param adminId
     * @return
     */
    List<Role> getRoleByAdminId(@RequestParam("adminId") Long adminId);

    List<Role> getRolesByIds(@RequestParam("ids") List<String> ids);

    List<Long> getRoleIdsByAdminId(@RequestParam("adminId") Long adminId);

    PageInfo<Role> getPageInfo(RoleDtoFilter filter);

    List<Role> getRoleList();
}