package com.tianmao.app.util;

import com.tianmao.app.config.AppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 视频缩略图工具类
 *
 * @author roach
 * @date 2017/12/9
 */
public final class VideoThumbnailUtil {

    private static final Logger logger = LoggerFactory.getLogger(VideoThumbnailUtil.class);

    /**
     * 视频缩略图后缀格式
     */
    public static final String THUMBNAIL_SUFFIX_FORMAT = ".jpg";

    private VideoThumbnailUtil() {

    }

    /**
     * 截取视频图片（传入视频什么路径，截取的图片也在该目录下）
     *
     * @param videoPath
     * @return videoThumbnail 视频缩略图路径
     */
    public static String processImg(String videoPath) {
        File file = new File(videoPath);
        if (!file.exists()) {
            logger.error("路径[{}]对应的视频文件不存在", videoPath);
            return "";
        }
        String ffmpegPath = AppContext.ffmpegPath;
        if (!isLinux()) {
            File ffmpegPathFile = new File(ffmpegPath);
            if (!ffmpegPathFile.exists()) {
                logger.error("ffmpeg[{}]路径错误", ffmpegPath);
                return "";
            }
        }
        String videoThumbnail = videoPath.substring(0, videoPath.lastIndexOf(".")) + THUMBNAIL_SUFFIX_FORMAT;
        List<String> commands = new ArrayList<>();
        commands.add(ffmpegPath);
        commands.add("-i");
        commands.add(videoPath);
        commands.add("-y");
        commands.add("-f");
        commands.add("image2");
        commands.add("-ss");
        commands.add("1");
        //commands.add("-t");
        //commands.add("1");//设置截取视频多少秒时的画面
        //commands.add("-s");
        //commands.add("360x360");
        commands.add(videoThumbnail);
        try {
            logger.info(commands.toString());
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command(commands);
            Process process = processBuilder.start();
            process.waitFor();//等待完成

            File thumbnailFile = new File(videoThumbnail);
            if (!thumbnailFile.exists()) {
                logger.warn("视频图片[{}]截取失败", videoThumbnail);
                return null;
            }
            return videoThumbnail;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private static boolean isLinux() {
        Properties prop = System.getProperties();
        String os = prop.getProperty("os.name");
        if (os != null && os.toLowerCase().indexOf("linux") > -1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        processImg("D:/0.mp4");
    }

}
