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
@FeignClient(name = "${server.app.name}")
public interface RoleService {

    /**
     * 更新和创建
     *
     * @param role 如果角色ID为空将创建，否则更新。
     * @return 创建成功后的ID，失败则为空字符串
     */
    @RequestMapping(value = "/role/update")
    boolean update(Role role) ;

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/role/delete")
    boolean delete(@RequestParam("roleId") Long roleId);

    /**
     * 获得单个角色
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/role")
    Role getRoleById(@RequestParam("roleId") Long roleId);

    /**
     * 获取用户对应的角色
     *
     * @param adminId
     * @return
     */
    @RequestMapping(value = "/role/adminId")
    List<Role> getRoleByAdminId(@RequestParam("adminId") Long adminId);

    @RequestMapping(value = "/role/roles")
    List<Role> getRolesByIds(@RequestParam("ids") List<String> ids);

    @RequestMapping(value = "/role/ids/adminId")
    List<Long> getRoleIdsByAdminId(@RequestParam("adminId") Long adminId);

    @RequestMapping(value = "/role/pageInfo")
    PageInfo<Role> getPageInfo(RoleDtoFilter filter);

    @RequestMapping(value = "/role/getRoleList")
    List<Role> getRoleList();
}