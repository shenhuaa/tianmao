/*
package com.tianmao.controller.app.export;

import cn.alphaidea.wancheleyuan.common.type.shop.OrderStatus;
import cn.alphaidea.wancheleyuan.common.type.shop.PayType;
import cn.alphaidea.wancheleyuan.common.type.shop.RefundStatus;
import com.alipay.api.domain.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

*/
/**
 * 订单表
 *
 * @author roach
 * @date 2018/1/3
 *//*

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "shop_order")
public class Order implements Serializable {

    private static final long serialVersionUID = -5542163860204969957L;
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    */
/**
     * '支付人appid',
     *//*

    private String appId;

    */
/**
     * '门店编号,
     *//*

    private Long shopId;

    */
/**
     * 订单编号
     *//*

    private String orderNumber;

    */
/**
     * '第三方支付流水号',
     *//*

    private String thirdPayNumber;

    */
/**
     * '支付类型 0:微信支付 1:支付宝支付',
     *//*

    private PayType payType;

    */
/**
     * '支付总金额',
     *//*

    private BigDecimal totalPrice;

    */
/**
     * '支付状态0:未支付 1:已支付',
     *//*

    private OrderStatus orderStatus;

    private Integer payPlatform;
    */
/**
     * 订单退款状态
     *//*

    private RefundStatus refundStatus;

    */
/**
     * 备注
     *//*

    private String remark;

    */
/**
     *
     *//*

    private Date addTime;

    */
/**
     * 支付时间
     *//*

    private Date payTime;

    */
/**
     * 商品明细
     *//*

    @Transient
    private List<Good> goods;

    */
/**
     * 商品key
     *//*

    @Transient
    private List<String> goodKeys;

    */
/**
     * 关键字查询
     *//*

    @Transient
    private String keyword;

    */
/**
     * 商店名称
     *//*

    @Transient
    private String shopName;

    //优惠金额
    @Transient
    private BigDecimal discountAmount = new BigDecimal(0);

    @Transient
    private Long channelId;

    //，订单总数，订单总金额。支付宝金额，微信金额money
    @Transient
    private Long ordersTotal;

    @Transient
    private BigDecimal ordersMoney;

    @Transient
    private BigDecimal alipayMoney;

    @Transient
    private BigDecimal weChatMoney;

    @Transient
    private BigDecimal avgOrderAmount;

    @Transient
    private BigDecimal maxOrderAmount;

    @Transient
    private BigDecimal minOrderAmount;

    @Transient
    private BigDecimal weChatRatio;

    @Transient
    private BigDecimal alipayRatio;

    @Transient
    private Date beginTime;

    @Transient
    private Date endTime;

    @Transient
    private String goodNames;

    @Transient
    private String goodCodes;

    @Transient
    private List<OrderDetail> detailList;

    */
/**
     * 退款金额
     *//*

    @Transient
    private BigDecimal refundAmout;
}
*/
