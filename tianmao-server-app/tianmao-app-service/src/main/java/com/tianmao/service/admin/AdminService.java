package com.tianmao.service.admin;

import com.github.pagehelper.PageInfo;
import com.tianmao.dto.user.AdminDtoFilter;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tianmao.model.user.Admin;

import java.util.List;

/**
 * admin用户服务
 * @author roach
 * @date 2017/12/2
 */
@FeignClient(name = "${server.app.name}")
public interface AdminService {

    @RequestMapping(value = "/admin/save")
    Admin save(Admin admin);

    /**
     * 通过ID获取账户详情
     *
     * @param adminId
     * @return
     */
    @RequestMapping(value = "/admin/id")
    Admin getAdminById(@RequestParam("adminId") Long adminId);

    @RequestMapping(value = "/admin/username")
    Admin getAdminByUsername(@RequestParam("username") String username);

    /**
     * 修改密码
     *
     * @param id
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/admin/updatePassword")
    boolean updatePassword(@RequestParam("id") Long id, @RequestParam("newPassword") String newPassword);

    @RequestMapping(value = "/admin/getPageInfo")
    PageInfo<Admin> getPageInfo(AdminDtoFilter filter);

    /**
     * 修改管理员状态
     * @param id
     * @param status
     * @return
     */
    @RequestMapping(value = "/admin/updateStatus")
    boolean updateStatus(@RequestParam("id") Long id, @RequestParam("status") Integer status);

    /**
     * 管理员修改
     * @param admin
     * @return
     */
    @RequestMapping(value = "/admin/update")
    boolean update(Admin admin);

    /**
     * 通过管理员获取角色集合
     * @param userId
     * @return
     */
    @RequestMapping(value = "/admin/getAdminRoleById")
    List<Admin> getAdminRoleById(@RequestParam("userId") Long userId);
}
