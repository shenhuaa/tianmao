package tianmao.web.app.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信支付配置文件 https://docs.open.alipay.com/270/alipay.trade.page.pay 文档地址
 *
 * @author roach
 * @date 2018/1/5
 */
@ConfigurationProperties(prefix = "alipay")
@Configuration
@Data
public class ChatAlipayConfig {
    /**
     * appid
     */
    private String appid;

    /**
     * 开发者私钥，由开发者自己生成
     */
    private String privateKey;

    /**
     * 应用公钥，由开发者自己生成
     */
    private String pulicKey;

    /**
     * 支付宝公钥，由支付宝生成
     */
    private String alipayPublicKey;

    /**
     * 参数返回格式，只支持json
     */
    private String format = "JSON";

    /**
     * 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
     */
    private String signType = "RSA2";

    /**
     * 编码集，支持GBK/UTF-8
     */
    private String charset = "UTF-8";

    /**
     * 支付宝网关（固定）
     */
    private String serverUrl;

    /**
     * 聊天室支付宝回调通知url
     */
    private String chatNotifyUrl;

    /**
     * 商城商品购买支付宝回调通知url
     */
    private String mallNotifyUrl;

    /**
     * 商城钱包充值支付宝回调通知url
     */
    private String walletNotifyUrl;

    /**
     * 支付完成后调转地址
     */
    private String returnUrl;

    /**
     * 订单标题
     */
    private String subject;



}