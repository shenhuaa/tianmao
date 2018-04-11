package tianmao.dto.user;

import lombok.Data;
import tianmao.common.PagingAttribute;
import tianmao.model.user.Role;

/**
 * 角色搜索过滤
 *
 * @author roach
 * @date 2017/12/2
 */
@Data
public class RoleDtoFilter extends Role {

    private PagingAttribute pagingAttribute;

}
