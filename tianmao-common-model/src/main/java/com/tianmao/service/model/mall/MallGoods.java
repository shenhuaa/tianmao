package com.tianmao.service.model.mall;

import com.tianmao.service.type.mall.MallOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品表
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "mall_goods")
public class MallGoods implements Serializable {

    private static final long serialVersionUID = -5542163860204969957L;
    @Id
    @GeneratedValue(generator = "JDBC")
    /**
     *
     */
    private Long id;
    /**
     * '商品名称',
     */
    private String goodName;
    /**
     * '商品条形码',
     */
    private String goodCode;
    /**
     * '商品子分类名称',
     */
    private String categorySonName;
    /**
     * '商品父分类名称',
     */
    private String categoryParentName;
    /**
     * '商品简介',
     */
    private String detail;
    /**
     * '商品价格',
     */
    private BigDecimal price;
    /**
     * '商品图片',
     */
    private String image;
    /**
     * '商品重量',
     */
    private BigDecimal weight;

    /**
     *
     */
    private Date addTime;

    private Date updateTime;
    /**
     * 进货价
     */
    private BigDecimal stockPrice;
    /**
     * 删除状态(0：正常  1：删除)
     */
    private Boolean deleted;

    private MallOrderStatus orderStatus;

    /**
     * 商品总数量
     */
    @Transient
    private int sumNumber;

    /**
     * 商品总金额
     */
    @Transient
    private BigDecimal sumMoney;

    /**
     * 库存剩余总数
     */
    @Transient
    private Integer storeCount;

    /**
     * 门店id
     */
    @Transient
    private Long shopId;

    /**
     * 保质期
     */
    @Transient
    private Date expireDate;





    @Transient
    private String imgs;

    @Transient
    private String goodCategory;

    @Transient
    private Long categoryOne;

    @Transient
    private Long categoryTwo;


}
