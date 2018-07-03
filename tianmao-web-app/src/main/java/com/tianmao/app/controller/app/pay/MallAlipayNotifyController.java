/*
package tianmao.web.app.controller.app.pay;

import com.alipay.api.internal.com.tianmao.util.AlipaySignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.com.tianmao.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tianmao.com.tianmao.common.BigDecimalUtil;
import tianmao.type.mall.MallOrderStatus;
import tianmao.web.app.com.tianmao.config.ChatAlipayConfig;
import tianmao.web.app.com.tianmao.util.Rest;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.com.tianmao.util.Enumeration;
import java.com.tianmao.util.HashMap;
import java.com.tianmao.util.Map;

*/
/**
 * 支付宝扫码支付异步通知
 *
 * @author roach
 * @date 2018/1/3
 *//*

@RestController
@RequestMapping("/mall/alipay/notify")
public class MallAlipayNotifyController {

    private static final Logger logger = LoggerFactory.getLogger(MallAlipayNotifyController.class);
    @Autowired
    private ChatAlipayConfig com.tianmao.config;

    @Autowired
    private MallOrderService mallOrderService;


    */
/**
     * 支付宝扫码支付异步通知
     *
     * @param out_trade_no 商户订单号
     * @param trade_no     支付宝交易号
     * @param trade_status 当前交易状态
     * @param total_amount 订单金额
     *//*

    @PostMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String notify(String out_trade_no, String trade_no, String trade_status, String total_amount, HttpServletRequest request) {
        Rest.Builder rest = Rest.newBuilder();
        Map<String, String> params = new HashMap<>();
        Enumeration em = request.getParameterNames();
        while (em.hasMoreElements()) {
            String name = (String) em.nextElement();
            String value = request.getParameter(name);
            params.put(name, value);
        }
        logger.debug("支付宝异步通知信息:[{}]", params);

        String notifyMessage = String.format("商户订单号: [%s], 支付宝交易号: [%s], 当前交易状态: [%s]", out_trade_no, trade_no, trade_status);
        if (!StringUtils.hasText(out_trade_no) || !StringUtils.hasText(trade_no) || !StringUtils.hasText(trade_status)) {
            logger.warn("参数错误:[{}]", notifyMessage);
            return "fail";
        }
        if ("WAIT_BUYER_PAY".equals(trade_status)) {
            logger.warn("等待用户支付:[{}]", notifyMessage);
            return "fail";
        }
        if (!"TRADE_SUCCESS".equals(trade_status)) {
            logger.warn("支付失败:[{}]", notifyMessage);
            return "fail";
        }
        try {
            String orderNumber = out_trade_no;
            MallOrder order = mallOrderService.getOrderByOrderNumber(orderNumber);
            if (order == null) {
                logger.warn("订单[{}]不存在, ", out_trade_no);
                return "fail";
            }
            if (MallOrderStatus.STAY_SEND == order.getOrderStatus()) {
                logger.warn("订单[{}]已支付, ", out_trade_no);
                return "success";
            }
            if (MallOrderStatus.NO_PAY != order.getOrderStatus()) {
                logger.warn("订单[{}]不是待付款状态, ", out_trade_no);
                return "success";
            }

            //总金额
            BigDecimal totalPrice = BigDecimal.valueOf(Double.valueOf(total_amount));
            if (!BigDecimalUtil.isZero(BigDecimalUtil.sub(order.getTotalPrice(), totalPrice))) {
                logger.warn("支付金额不相等，充值金额[{}], 支付金额[{}]", order.getTotalPrice(), totalPrice);
                return "fail";
            }

            //签名认证
            boolean checkSign = AlipaySignature.rsaCheckV1(params, com.tianmao.config.getAlipayPublicKey(), com.tianmao.config.getCharset(), com.tianmao.config.getSignType());
            if (!checkSign) {
                logger.warn("支付宝订单[{}]签名失败，无法完成交易!", out_trade_no);
                return "fail";
            }
            //更新订单状态
            mallOrderService.updateOrderStatus(order.getId(), trade_no, MallOrderStatus.STAY_SEND);
            System.out.println("回调方法支付宝支付红包成功！");
            return "success";
        } catch (Exception e) {
            logger.error("支付宝支付通知未知错误：[{}]", e);
        }
        return "fail";
    }



}
*/
