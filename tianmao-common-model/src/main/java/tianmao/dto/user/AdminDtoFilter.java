package tianmao.dto.user;

import lombok.Data;
import tianmao.common.PagingAttribute;
import tianmao.model.user.Admin;

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
