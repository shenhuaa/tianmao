package tianmao.admin.mapper;


import tianmao.model.user.Admin;
import tianmao.mybatis.BaseMapper;

import java.util.List;

public interface AdminMapper extends BaseMapper<Admin> {

    Admin getAdminByUsername(String username);

    List<Admin> getList(Admin admin);

    boolean updateAdminByAdminId(Admin admin);

    List<Admin> getAdminRoleById(Long userId);
}