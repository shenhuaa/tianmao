package com.tianmao.service.admin;

import com.github.pagehelper.PageInfo;
import com.tianmao.service.dto.user.AdminDtoFilter;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tianmao.service.model.user.Admin;

import java.util.List;

/**
 * admin用户服务
 * @author roach
 * @date 2017/12/2
 */
public interface AdminService {

    Admin save(Admin admin);

    /**
     * 通过ID获取账户详情
     *
     * @param adminId
     * @return
     */
    Admin getAdminById(@RequestParam("adminId") Long adminId);

    Admin getAdminByUsername(@RequestParam("username") String username);

    /**
     * 修改密码
     *
     * @param id
     * @param newPassword
     * @return
     */
    boolean updatePassword(@RequestParam("id") Long id, @RequestParam("newPassword") String newPassword);

    PageInfo<Admin> getPageInfo(AdminDtoFilter filter);

    /**
     * 修改管理员状态
     * @param id
     * @param status
     * @return
     */
    boolean updateStatus(@RequestParam("id") Long id, @RequestParam("status") Integer status);

    /**
     * 管理员修改
     * @param admin
     * @return
     */
    boolean update(Admin admin);

    /**
     * 通过管理员获取角色集合
     * @param userId
     * @return
     */
    List<Admin> getAdminRoleById(@RequestParam("userId") Long userId);
}
