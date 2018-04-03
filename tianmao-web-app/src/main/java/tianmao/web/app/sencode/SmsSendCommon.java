package tianmao.web.app.sencode;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import tianmao.common.DateUtil;
import tianmao.web.app.config.SMS;

import java.text.MessageFormat;

@Component
public class SmsSendCommon  {
    private static final Logger logger = LoggerFactory.getLogger(SmsSendCommon.class);

    public boolean smsSend(@RequestBody SMS sms) {
        String timestamp = DateUtil.getNowTime(DateUtil.DATE_All_KEY_STR);
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append(sms.getUsername())
                .append(sms.getPassword())
                .append(timestamp);
        //签名
        String sign = DigestUtils.md5Hex(sbuilder.toString());

        //模板内容
        String content = MessageFormat.format(sms.getContent(), String.valueOf(sms.getCode()));

        OkHttpClient httpClient = new OkHttpClient();
        FormBody body = new FormBody.Builder()
                .add("action", "send")
                .add("userid", sms.getCompanyId())
                .add("timestamp", timestamp)
                .add("sign", sign)
                .add("mobile", sms.getMobile())
                .add("content", content)
                .build();
        try {
            Response response = httpClient.newCall(new Request.Builder()
                    .url(sms.getUrl())
                    .post(body)
                    .build()).execute();
            String responseBody = response.body().string();
            if (response.code() == 200) {
                if (responseBody.contains("Success")) {
                    return true;
                }
                logger.error("发送验证码失败：[{}]", responseBody);
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }
}
