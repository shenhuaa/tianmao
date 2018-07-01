package com.tianmao.app.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * 获取网站地址
 *
 * @author roach
 * @date 2017/12/1
 */
@Data
@Configuration
public class AppContext implements ServletContextAware {


    /**
     * 管理后台根网址
     */
    public static String adminUrl;
    /**
     * 上传图片路径
     */

    public static String imgurl;
    /**
     * 上传视频路径
     */

    public static String videourl;

    /**
     * app根网址
     */
    public static String appUrl;

    /**
     *app默认图片
     */
    public static String appDefaultImg;


    /**
     * 图片路径
     */
    public static String imageUrl;

    /**
     * 文件存储前缀
     */
    public static String storagePrefix;

    /**
     * 判断是app应用
     */
    public static boolean isWebApp;

    /**
     * 判断是否为webMall应用
     */
    public static boolean webMall;

    /**
     * 应用环境
     */
    public static String active;

    /**
     * ffmpeg路径
     */
    public static String ffmpegPath;

    /**
     * 图片后缀大小
     */
    public static String imgSize;

    /**
     * 水印图片路径
     */
    public static String watermarkPath;

    /**
     * 视频图片水印图
     */
    public static String playWatermarkPath;

    @Value("${web.admin.url}")
    public void setAdminUrl(String adminUrl) {
        AppContext.adminUrl = adminUrl;
    }

    public String getAdminUrl() {
        return AppContext.adminUrl;
    }


    /**
     * 当前应用名称
     */
    @Value("${spring.application.name}")
    private String springApplicationName;

    /**
     * web app名称
     */
    @Value("${web.app.name}")
    private String webAppName;

    @Value("${storage.path.talk.img}")
    public void setImgurl(String imgurl) {
        AppContext.imgurl = imgurl;
    }


    @Value("${storage.path.talk.video}")
    public void setVideourl(String videourl) {
        AppContext.videourl = videourl;
    }




    @Value("${storage.default}")
    public void setAppDefaultImg(String appDefaultImg) {
        AppContext.appDefaultImg = appDefaultImg;
    }
    public String getAppDefaultImg() {
        return AppContext.appDefaultImg;
    }

    @Value("${web.image.imgsize}")
    public void setImgSize(String imgSize) {
        AppContext.imgSize = imgSize;
    }

    public String getImgSize() {
        return AppContext.imgSize;
    }

    public String getAppUrl() {
        return AppContext.appUrl;
    }

    @Value("${web.app.url}")
    public void setAppUrl(String appUrl) {
        AppContext.appUrl = appUrl;
    }

    @Value("${spring.profiles.active}")
    public void setActive(String active) {
        AppContext.active = active;
        AppContext.isWebApp = springApplicationName.equals(webAppName);
    }



    @Value("${web.image.url}")
    public void setImageUrl(String imageUrl) {
        AppContext.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return AppContext.imageUrl;
    }

    public String getImgurl() {

        return AppContext.imgurl;
    }

    @Value("${storage.prefix}")
    public void setStoragePrefix(String storagePrefix) {
        AppContext.storagePrefix = storagePrefix;
    }



    @Value("${web.ffmpeg}")
    public void setFfmpegPath(String ffmpegPath) {
        AppContext.ffmpegPath = ffmpegPath;
    }

    @Value("${storage.path.watermark}")
    public void setWatermarkPath(String watermarkPath) {
        AppContext.watermarkPath = watermarkPath;
    }

    @Value("${storage.path.play.watermark}")
    public void setPlayWatermarkPath(String playWatermarkPath) {
        AppContext.playWatermarkPath = playWatermarkPath;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        servletContext.setAttribute("ctx", this);
    }

}