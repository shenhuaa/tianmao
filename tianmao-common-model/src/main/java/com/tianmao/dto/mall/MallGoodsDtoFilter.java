package com.tianmao.dto.mall;


import com.tianmao.common.PagingAttribute;
import com.tianmao.model.mall.MallGoods;
import lombok.Data;

@Data
public class MallGoodsDtoFilter extends MallGoods {
    private PagingAttribute pagingAttribute;
}
