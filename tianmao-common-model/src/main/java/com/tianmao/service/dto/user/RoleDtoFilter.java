package com.tianmao.service.dto.user;

import com.tianmao.service.model.user.Role;
import lombok.Data;
import com.tianmao.service.common.PagingAttribute;

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
