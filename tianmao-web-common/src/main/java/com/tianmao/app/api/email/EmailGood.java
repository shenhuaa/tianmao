package com.tianmao.app.api.email;

import com.tianmao.app.config.AppContext;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 邮件发送
 * @author roach
 * @date 2018/5/16
 */
@Component
public class EmailGood {

    private static final Logger logger = LoggerFactory.getLogger(EmailGood.class);
    private  static String BASE_PACKAGE_PATH = "/templates/multicountry";
    private  static String TEMPLATE_NAME = "mail-content.ftl";

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailProperties mailProperties;

    @Autowired
    private FreeMarkerConfigurer freemarker;

    /**
     * 模板邮件
     * @param sendTo
     * @param verifyCode
     */
    public boolean sendTemplateMail(String sendTo, int verifyCode) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(mailProperties.getUsername(), AppContext.appName);
            helper.setTo(sendTo);
            helper.setSubject(AppContext.subject);
            String text = this.mailContent(verifyCode);
            helper.setText(text, true);
            mailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 获取模板内容
     *
     * @param verifyCode
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    private String mailContent(int verifyCode) throws IOException, TemplateException {
        freemarker.template.Configuration configuration = freemarker.getConfiguration();
        configuration.setClassForTemplateLoading(this.getClass(), BASE_PACKAGE_PATH);
        Template template;
        template = configuration.getTemplate(this.TEMPLATE_NAME);
        Map<String, Object> map = new HashMap<>();
        map.put("appUrl", AppContext.appUrl);
        map.put("verifyCode", verifyCode);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
    }

}
