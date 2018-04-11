package tianmao.admin.mapper;

import org.apache.ibatis.annotations.Param;
import tianmao.model.user.Permission;

import java.util.List;
import java.util.Set;

/**
 * Created by roach on 17/6/5.
 */
public interface PermissionWithRoleMapper {

    List<Permission> getListByAdminId(Long adminId);

    List<Permission> getListByRoleId(Long roleId);

    boolean createByRoleId(@Param("roleId") Long roleId, @Param("permissions") List<Permission> permissions);

    boolean deleteByRoleId(Long roleId);

    Set<String> getPermissionMarksByAdminId(Long adminId);

}