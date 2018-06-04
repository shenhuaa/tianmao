package com.tianmao.common;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransformUtil {

    /**
     * 重内容获取第一张图片的语法
     * @param content
     * @return
     */
    public static String getImgUrl(String content) {
        if(StringUtils.isBlank(content)){
            return null;
        }
        String img = "";
        Pattern p_image;
        Matcher m_image;
        // 图片链接地址
        String regEx_img = "img src\\s*=\\s*\"?(.*?)(\"|>|\\s+)";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(content);
        while (m_image.find()) {
            img = img + "," + m_image.group();
            System.out.println("img:" + img);
            // 匹配src
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                return  m.group(1);
            }
        }
        return null;
    }

    /**
     * 富文本转换HTML标签
     * @param htmlStr
     * @return
     */
    public static String removeHtml(String htmlStr) {
        htmlStr = StringEscapeUtils.unescapeHtml4(htmlStr);
        return htmlStr.trim(); // 返回文本字符串
    }
}
