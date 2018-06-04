package com.tianmao.dto.user;

import com.tianmao.model.user.Role;
import lombok.Data;
import com.tianmao.common.PagingAttribute;

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
