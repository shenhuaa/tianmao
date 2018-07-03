package com.tianmao.service.admin.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tianmao.service.admin.mapper.AdminWithRoleMapper;
import com.tianmao.utils.HttpCode;
import com.tianmao.service.dto.user.AdminDtoFilter;
import com.tianmao.service.model.user.Admin;
import com.tianmao.service.admin.AdminService;
import com.tianmao.utils.Sha512HashPasswordUtil;
import com.tianmao.service.type.user.UserStatus;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tianmao.service.admin.mapper.AdminMapper;
import com.tianmao.utils.PagingAttribute;
import com.tianmao.service.exception.BaseServiceException;

import java.util.List;

/**
 * admin用户服务
 *
 * @author roach
 * @date 2017/12/2
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminWithRoleMapper adminWithRoleMapper;

    private static final String PASSWORD = "**********";

    @Override
    public Admin save(@RequestBody Admin admin) {
        Admin ad = new Admin();
        ad.setUsername(admin.getUsername());
        if (adminMapper.selectOne(ad) != null) {
            throw new BaseServiceException(HttpCode.ILLEGAL_PARAMETERS, "该管理员账号存在了!");
        }
        Long roleId = admin.getRoleId();
        String salt = Sha512HashPasswordUtil.getSalt();
        String hashPassword = Sha512HashPasswordUtil.getHashPassword(admin.getPassword(), salt);
        admin.setPasswordSalt(salt);
        admin.setPassword(hashPassword);
        if (adminMapper.insertSelective(admin) < 1) {
            Assert.isTrue(false, "修改管理员状态失败!");
        }
        boolean result = adminWithRoleMapper.insertAdminWithRole(roleId, admin.getId());
        Assert.isTrue(result, "添加管理员失败!");
        return admin;
    }

    @Override
    public boolean updatePassword(Long id, String newPassword) {
        String salt = Sha512HashPasswordUtil.getSalt();
        String newPasswordHashed = Sha512HashPasswordUtil.getHashPassword(newPassword, salt);
        return false;
    }

    @Override
    public Admin getAdminById(Long adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminMapper.getAdminByUsername(username);
    }

    @Override
    public PageInfo<Admin> getPageInfo(@RequestBody AdminDtoFilter filter) {
        PagingAttribute page = filter.getPagingAttribute();
        PageHelper.startPage(page.getPageNumber(), page.getPageSize(), page.getOrderBy());
        List<Admin> adminList = adminMapper.getList(filter);
        return new PageInfo<>(adminList);
    }

    /**
     * 修改管理员状态
     *
     * @param id
     * @param status
     * @return
     */
    @Override
    public boolean updateStatus(Long id, Integer status) {
        Admin admin = adminMapper.selectByPrimaryKey(id);
        if (admin == null) {
            throw new BaseServiceException(HttpCode.ILLEGAL_PARAMETERS, "管理员不存在");
        }
        if (status == 0) {
            admin.setStatus(UserStatus.NORMAL);
        }
        if (status == 1) {
            admin.setStatus(UserStatus.FREEZE);
        }
        if (adminMapper.updateByPrimaryKeySelective(admin) < 1) {
            Assert.isTrue(false, "修改管理员状态失败!");
        }
        return true;
    }

    @Override
    public boolean update(@RequestBody Admin admin) {
        Admin oldAdmin = adminMapper.selectByPrimaryKey(admin.getId());
        String password = admin.getPassword();
        if (!PASSWORD.equals(password)) {
            String salt = Sha512HashPasswordUtil.getSalt();
            String hashPassword = Sha512HashPasswordUtil.getHashPassword(password, salt);
            admin.setPassword(hashPassword);
            admin.setPasswordSalt(salt);
        } else {
            admin.setPassword(oldAdmin.getPassword());
            admin.setPasswordSalt(oldAdmin.getPasswordSalt());
        }
        boolean result = adminWithRoleMapper.updateAdminWithRole(admin.getRoleId(), admin.getId());
        Assert.isTrue(result, "修改管理员角色失败!");

        result = adminMapper.updateAdminByAdminId(admin);
        Assert.isTrue(result, "修改管理员状态失败!");

        return true;
    }

    /**
     * 通过管理员获取角色集合
     *
     * @param userId
     * @return
     */
    @Override
    public List<Admin> getAdminRoleById(Long userId) {
        return adminMapper.getAdminRoleById(userId);
    }

}