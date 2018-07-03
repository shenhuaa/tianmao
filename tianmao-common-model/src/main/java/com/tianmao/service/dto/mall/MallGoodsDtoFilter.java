package com.tianmao.service.dto.mall;


import com.tianmao.utils.PagingAttribute;
import com.tianmao.service.model.mall.MallGoods;
import lombok.Data;

@Data
public class MallGoodsDtoFilter extends MallGoods {
    private PagingAttribute pagingAttribute;
}
