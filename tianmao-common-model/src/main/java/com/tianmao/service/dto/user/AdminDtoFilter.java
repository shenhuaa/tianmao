package com.tianmao.service.dto.user;

import com.tianmao.utils.PagingAttribute;
import com.tianmao.service.model.user.Admin;
import lombok.Data;

/**
 * Admin搜索过滤
 *
 * @author roach
 * @date 2017/11/18
 */
@Data
public class AdminDtoFilter extends Admin {

    private PagingAttribute pagingAttribute;

}
