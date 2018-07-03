package com.tianmao.service.dto.mall;

import com.tianmao.utils.PagingAttribute;
import com.tianmao.service.model.quartzs.QuartzTask;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * QuartzTask搜索过滤
 *
 * @author roach
 * @date 2018/3/15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QuartzTaskDtoFilter extends QuartzTask {

    private PagingAttribute pagingAttribute;

}
