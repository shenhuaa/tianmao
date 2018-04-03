/*
package tianmao.web.app.controller.app.pay;

import com.github.wxpay.sdk.WXPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tianmao.common.BigDecimalUtil;
import tianmao.type.mall.MallOrderStatus;
import tianmao.web.app.config.WxChatpayConfig;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

*/
/**
 * 支付宝扫码支付异步通知
 *
 * @author roach
 * @date 2018/1/3
 *//*

@Controller
@RequestMapping("/mall/weixin/pay/notify")
public class MallWeixinNotifyController {

    private static final Logger logger = LoggerFactory.getLogger(MallWeixinNotifyController.class);
    @Autowired
    private WxChatpayConfig config;

    @Autowired
    private MallOrderService mallOrderService;


    */
/**
     * 微信扫码支付异步通知
     *//*

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public String notify(HttpServletRequest request) throws IOException {
        String resultData = WeixinUtil.readInput(request.getInputStream());
        if (!StringUtils.hasText(resultData)) {
            logger.warn("微信支付后异步通知信息为空");
            return WeixinUtil.fail();
        }
        try {
            Map<String, String> dataMap = WXPayUtil.xmlToMap(resultData);
            String resultCode = dataMap.get("result_code");
            logger.debug("微信支付异步通知信息:[{}]", dataMap);
            boolean heckSign = WXPayUtil.isSignatureValid(dataMap, config.getKey(), config.getSignType());
            if (!heckSign) {
                logger.warn("微信签名失败[{}]", dataMap);
                return WeixinUtil.fail();
            }
            //微信支付订单号
            String trade_no = dataMap.get("transaction_id");
            String out_trade_no = dataMap.get("out_trade_no");
            String orderNumber = out_trade_no;
            MallOrder order = mallOrderService.getOrderByOrderNumber(orderNumber);
            if (order == null) {
                logger.warn("订单[{}]不存在, ", out_trade_no);
                return WeixinUtil.fail();
            }
            if (MallOrderStatus.STAY_SEND == order.getOrderStatus()) {
                logger.warn("订单[{}]已支付, ", out_trade_no);
                return WeixinUtil.success();
            }
            if (MallOrderStatus.NO_PAY != order.getOrderStatus()) {
                logger.warn("订单[{}]不是待付款状态, ", out_trade_no);
                return "success";
            }

            //总金额
            String totalFee = dataMap.get("total_fee");
            BigDecimal totalPrice = BigDecimalUtil.div(BigDecimal.valueOf(Double.valueOf(totalFee)), new BigDecimal(100));
            if (!BigDecimalUtil.isZero(BigDecimalUtil.sub(order.getTotalPrice(), totalPrice))) {
                logger.warn("支付金额不相等，充值金额[{}], 支付金额[{}]", order.getTotalPrice(), totalPrice);
                return WeixinUtil.fail();
            }
            //更新订单状态
            mallOrderService.updateOrderStatus(order.getId(), trade_no, MallOrderStatus.STAY_SEND);
            logger.debug("回调方法微信支付成功");
            return WeixinUtil.success();
        } catch (Exception e) {
            logger.error("微信支付后异步通知异常", e);
        }

        return WeixinUtil.fail();
    }

    public static void main(String[] args) {
        BigDecimal totalPrice = BigDecimalUtil.div(BigDecimal.valueOf(Double.valueOf(1)), new BigDecimal(100));
        System.out.println("totalPrice2 = " + new BigDecimal(1));
        if (!BigDecimalUtil.isZero(BigDecimalUtil.sub(new BigDecimal(1), totalPrice))) {
            System.out.println("totalPrice = " + totalPrice);
        }
    }
}
*/
