package tianmao.dto.mall;


import lombok.Data;
import tianmao.model.mall.MallGoods;
import tianmao.common.PagingAttribute;

@Data
public class MallGoodsDtoFilter extends MallGoods {
    private PagingAttribute pagingAttribute;
}
