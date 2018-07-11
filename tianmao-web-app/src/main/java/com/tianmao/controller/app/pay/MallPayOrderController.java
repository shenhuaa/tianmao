/*
package tianmao.web.app.controller.app.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.com.tianmao.util.AlipaySignature;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import io.swagger.annotations.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tianmao.com.tianmao.common.BigDecimalUtil;
import tianmao.com.tianmao.common.DateUtil;
import tianmao.com.tianmao.common.HttpCode;
import tianmao.com.tianmao.common.RemoteClientIpUtil;
import tianmao.service.app.UserService;
import tianmao.service.com.tianmao.exception.BaseServiceException;
import tianmao.type.mall.MallOrderStatus;
import tianmao.web.app.com.tianmao.config.ChatAlipayConfig;
import tianmao.web.app.com.tianmao.config.WxChatpayConfig;
import tianmao.web.app.com.tianmao.util.Rest;
import tianmao.web.app.com.tianmao.util.UUIDUtil;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.com.tianmao.util.Date;
import java.com.tianmao.util.HashMap;
import java.com.tianmao.util.Map;
import java.com.tianmao.util.TreeMap;

*/
/**
 * 商城支付
 *//*

@Api(tags = "商城支付下单", description = "MallOrderController")
@RestController
@RequestMapping("/mall/pay")
public class MallPayOrderController {

    */
/**
     * 支付宝:接口文档https://docs.open.alipay.com/204/105465/
     * 微信app支付: https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_1
     *//*


    private static final Logger logger = LoggerFactory.getLogger(MallPayOrderController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ChatAlipayConfig alipayConfig;

    @Autowired
    private WxChatpayConfig wxpayConfig;

    //@Autowired
    //private MallOrderService mallOrderService;

    @ApiOperation(value = "获取支付宝,微信准备支付的请求参数", httpMethod = "POST", response = Rest.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "timestamp", value = "时间戳", dataType = "int"),
            @ApiImplicitParam(name = "sign", value = "签名", dataType = "string"),
            @ApiImplicitParam(name = "token", value = "用户token", dataType = "string"),
            @ApiImplicitParam(name = "money", value = "总金额", required = true, dataType = "double"),
            @ApiImplicitParam(name = "payType", value = "支付类型(0:微信 1:支付宝)", dataType = "PayType"),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "int"),
            @ApiImplicitParam(name = "orderNumber", value = "订单编号", required = true, dataType = "string")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 500, message = "支付异常"),
            @ApiResponse(code = 40100, message = "该订单被支付过 : 返回订单编号   和支付类型  0:微信  1:支付宝"),
    })
    @RequestMapping("/update")
    public Rest createOrder(BigDecimal money, PayType payType, Long userId, String orderNumber) throws AlipayApiException {
        Rest.Builder rest = Rest.newBuilder();
        if (userId == null) {
            logger.warn("缺少用户参数");
            return rest.code(HttpCode.MISSING_PARAMETERS).message("缺少用户参数").build();
        }

        if (orderNumber == null) {
            logger.warn("缺少订单参数");
            return rest.code(HttpCode.MISSING_PARAMETERS).message("缺少订单编号参数").build();
        }
        if (userService.getUserById(userId) == null) {
            logger.warn("用户对象不存在");
            return rest.code(HttpCode.MISSING_PARAMETERS).message("当前用户不存在").build();
        }
        if (money == null || money.doubleValue() <= 0) {
            logger.warn("金额不能空或者金额小于0");
            return rest.code(HttpCode.MISSING_PARAMETERS).message("金额不能空或者金额小于0").build();
        }
        if (payType == null) {
            logger.warn("支付类型不存在");
            return rest.code(HttpCode.MISSING_PARAMETERS).message("支付类型不存在").build();
        }
        if (payType != PayType.ALIPAY && payType != PayType.WXPAY) {
            logger.warn("支付类型有误");
            return rest.code(HttpCode.MISSING_PARAMETERS).message("支付类型有误").build();
        }
        //更新订单支付类型
        MallOrder orders = mallOrderService.getOrderByOrderNumber(orderNumber);
        if(orders.getOrderStatus() == MallOrderStatus.STAY_SEND) {
            logger.warn("该订单已经被支付了");
            rest.put("payType",orders.getPayType());
            rest.put("orderNumber",orders.getOrderNumber());
            return rest.code(HttpCode.ORDER_IS_PAY).message("该订单已经被支付").build();
        }
        MallOrderStatus orderStatus = orders.getOrderStatus();
        if(orderStatus != MallOrderStatus.NO_PAY) {
            return rest.code(HttpCode.ILLEGAL_PARAMETERS).message(orderStatus.getRemark()).build();
        }
        //获取订单对象
        MallOrder order = mallOrderService.updateOrder(orderNumber,payType);
        //判断传进来的金额跟数据库保存的金额是否一致
       if (order.getTotalPrice().doubleValue() != money.doubleValue()) {
            logger.warn("后台跟前台的总金额不一样[{}]",money);
            return rest.code(HttpCode.MISSING_PARAMETERS).message("金额不一致").build();
        }
        //判断用户id是否一致
        if(!order.getUserId().equals(userId)) {
            logger.warn("不一致的用户id[{}]",userId);
            return rest.code(HttpCode.MISSING_PARAMETERS).message("不一致的用户参数").build();
        }
        //如果是支付宝支付
        if (payType == PayType.ALIPAY) {
            return aliPay(order.getOrderNumber(), order.getTotalPrice());
        }
        //如果是微信支付
        if (payType == PayType.WXPAY) {
            return wxPay(order.getOrderNumber(), order.getTotalPrice());
        }

        return rest.code(HttpCode.MISSING_PARAMETERS).message("支付类型不正确").build();
    }

    */
/**
     * 支付宝支付
     *
     * @param orderNumber 订单编号
     * @param money       订单金额
     * @return
     *//*

    private Rest aliPay(String orderNumber, BigDecimal money) {
        Rest.Builder rest = Rest.newBuilder();
        StringBuilder builder = new StringBuilder();
        Map<String, String> map = new TreeMap<>();
        //基本参数
        JSONObject jsonObject = new JSONObject();
        map.put("app_id", alipayConfig.getAppid());
        map.put("method", "alipay.trade.app.pay");
        map.put("charset", alipayConfig.getCharset());
        map.put("sign_type", alipayConfig.getSignType());
        map.put("timestamp", DateUtil.formatDate(new Date()));
        map.put("version", "1.0");
        map.put("notify_url", alipayConfig.getMallNotifyUrl());
        jsonObject.put("subject", "商城支付宝支付");
        jsonObject.put("total_amount", money.toString());
        jsonObject.put("out_trade_no", orderNumber);
        jsonObject.put("product_code", "QUICK_MSECURITY_PAY");
        try {
            //获取签名
            map.put("biz_content", jsonObject.toString());
            String content = AlipaySignature.getSignCheckContentV2(map);
            String sign = AlipaySignature.rsa256Sign(content, alipayConfig.getPrivateKey(), alipayConfig.getCharset());
            map.put("sign", sign);
            //拼接字符串
            for (String key : map.keySet()) {
                builder.append(key);
                builder.append("=");
                builder.append(URLEncoder.encode(map.get(key), alipayConfig.getCharset()));
                builder.append("&");
            }
            builder.deleteCharAt(builder.length() - 1);
            return rest
                    .put("aliData", builder.toString())
                    .put("orderNumber", orderNumber)
                    .code(HttpCode.OK).build();
        } catch (Exception e) {
            logger.error("支付宝生成订单失败", e);
        }
        throw new BaseServiceException(HttpCode.ERROR, "支付宝出错");

    }

    */
/**
     * 微信预下单
     * @param orderNumber 订单编号
     * @param money       订单金额
     * @return
     *//*

    private Rest wxPay(String orderNumber, BigDecimal money) {
        Rest.Builder rest = Rest.newBuilder();
        BigDecimal totalFee = BigDecimalUtil.mul(money, new BigDecimal(100));
        try {
            //统一下单请求数据
            Map<String, String> data = new TreeMap<>();
            data.put("appid", wxpayConfig.getAppID());
            data.put("mch_id", wxpayConfig.getMchID());
            data.put("body", "爱士多商城微信支付");
            data.put("out_trade_no", orderNumber);
            data.put("total_fee", String.valueOf(totalFee.intValue()));
            data.put("notify_url", wxpayConfig.getMallNotifyUrl());
            data.put("trade_type", wxpayConfig.getChatTradeType());
            data.put("nonce_str", UUIDUtil.getUUID());
            data.put("spbill_create_ip", RemoteClientIpUtil.getRemoteIP());
            String signatureOne = WXPayUtil.generateSignature(data, wxpayConfig.getKey());
            data.put("sign", signatureOne);
            WXPay wxpay = new WXPay(wxpayConfig, wxpayConfig.getSignType());
            Map<String, String> resultMap = wxpay.unifiedOrder(data);
            String code = resultMap.get("return_code");
            switch (code) {
                case "SUCCESS":
                    logger.debug("微信预下单生成成功：[{}]", resultMap);
                    //预支付交易会话ID
                    String prepayId = resultMap.get("prepay_id");
                    WXPayConstants.SignType signType = wxpayConfig.getSignType();
                    String uuid = UUIDUtil.getUUID();
                    String now = String.valueOf(new Date().getTime() / 1000);
                    //获取签名参数
                    Map<String, String> signatureMap = new HashMap<>();
                    signatureMap.put("appid", wxpayConfig.getAppID());
                    signatureMap.put("partnerid", wxpayConfig.getMchID());
                    signatureMap.put("prepayid", prepayId);
                    signatureMap.put("noncestr", uuid);
                    signatureMap.put("timestamp", now);
                    signatureMap.put("package", "Sign=WXPay");
                    try {
                        String signature = WXPayUtil.generateSignature(signatureMap, wxpayConfig.getKey(), signType);
                        //调起支付接口请求数据
                        signatureMap.put("sign", signature);
                        return rest
                                .put("wxData", signatureMap)
                                .put("orderNumber", orderNumber)
                                .code(HttpCode.OK).build();
                    } catch (Exception e) {
                        logger.error("获取签名出错", e);
                    }
                    break;

                case "FAIL":
                    logger.error("微信支付预下单失败:[{}]", resultMap);
                    break;

                default:
                    logger.error("微信不支持的交易状态，交易返回异常:[{}]", resultMap);
                    break;
            }
        } catch (Exception e) {
            logger.error("微信生成订单出错", e);
        }
        throw new BaseServiceException(HttpCode.ERROR, "微信生成订单出错");
    }
}
*/
