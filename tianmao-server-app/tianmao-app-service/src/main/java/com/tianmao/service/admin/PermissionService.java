package com.tianmao.service.admin;

import com.tianmao.service.model.user.Permission;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * 用户权限
 * <p/>
 * Created by roach on 17/6/8.
 */
public interface PermissionService {

    /**
     * 根据用户id获取用户权限
     */
    List<Permission> getListByUid(@RequestParam("uid") Long uid);

    /**
     * 根据角色获取权限菜单
     */
    List<Long> getPermissionIdsByRoleId(@RequestParam("roleId") Long roleId);

    /**
     * 获取权限列表
     */
    List<Permission> getList();


    /**
     * 获取指定角色的权限列表
     */
    List<Permission> getListByRoleId(@RequestParam("roleId") Long roleId);

    /**
     * 获取单个权限
     *
     * @param id
     * @return
     */
    Permission getPermissionById(@RequestParam("id") Long id);

    /**
     * 创建或更新权限资料
     *
     * @param permission
     * @return
     */
    boolean save(Permission permission);

    /**
     * 通过用户编号和模块名字获取他的所有权限
     *
     * @param adminId 用户编号
     * @return 拥有的权限标识
     */
    Set<String> getPermissionMarksByAdminId(@RequestParam("adminId") Long adminId);

    /**
     * 获取所有权限，不递归
     *
     * @return 所有权限
     */
    List<Permission> getAllPermissions();

    /**
     * 获取角色所拥有的权限
     *
     * @param roleId
     * @return
     */
    List<Long> getPermissionIds(@RequestParam("roleId") Long roleId);

    /**
     * 判断用户是否有某个权限
     *
     * @param adminId    用户id
     * @param permission 权限名称
     * @return
     */
    boolean checkPermission(@RequestParam("adminId") Long adminId, @RequestParam("permission") String permission);

    /**
     * 获取单个菜单对应的权限
     *
     * @param menuId
     * @return
     */
    List<Long> getListByMenuId(@RequestParam("menuId") Long menuId);

}