package com.tianmao.web.app.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.tianmao.web.app.config.AppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * 二维码工具类
 *
 * @author roach
 * @date 2018/1/3
 */
public final class ZxingUtil {

    private static final Logger logger = LoggerFactory.getLogger(ZxingUtil.class);
    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "jpg";

    // 二维码尺寸
    private static final int QRCODE_WIDTH = 500;

    private ZxingUtil() {

    }

    public static void writeQRCodeImage(String content, String savePath) {
        try {
            BufferedImage image = getBufferedImage(content);
            ImageIO.write(image, FORMAT_NAME, new File(savePath));
        } catch (Exception e) {
            logger.error("生成二维码出错:", e);
        }
    }

    /**
     * 生成支付宝二维码
     *
     * @param content  内容
     * @param response
     */
    public static void getQRCodeImage(String content, HttpServletResponse response) {
        try {
            BufferedImage image = getBufferedImage(content);
            ImageIO.write(image, FORMAT_NAME, response.getOutputStream());
        } catch (Exception e) {
            logger.error("生成二维码出错:", e);
        }
    }

    private static BufferedImage getBufferedImage(String content) {
        try {
            return ZxingUtil.createImage(content, AppContext.storagePrefix + "/upload/default.png", true);
        } catch (Exception e) {
            logger.error("生成二维码出错:", e);
        }
        return null;
    }

    private static BufferedImage createImage(String content, String imgPath, boolean needCompress) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_WIDTH, QRCODE_WIDTH, hints);
        int bitWidth = bitMatrix.getWidth();
        int bitHeight = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(bitWidth, bitHeight, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < bitWidth; x++) {
            for (int y = 0; y < bitHeight; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        // 插入图片
        ZxingUtil.insertImage(image, imgPath, needCompress);
        return image;
    }

    /**
     * 插入LOGO
     *
     * @param source       二维码图片
     * @param imgPath      LOGO图片地址
     * @param needCompress 是否压缩
     * @throws Exception
     */
    private static void insertImage(BufferedImage source, String imgPath, boolean needCompress) throws Exception {
        File file = new File(imgPath);
        if (!file.exists()) {
            logger.warn("logo文件不存在：[{}]", imgPath);
            return;
        }
        Image src = ImageIO.read(file);
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            int smallWidth = QRCODE_WIDTH / 5;
            if (width > smallWidth) {
                width = smallWidth;
            }
            if (height > smallWidth) {
                height = smallWidth;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_WIDTH - width) / 2;
        int y = (QRCODE_WIDTH - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }


    /**
     * 创建临时文件
     *
     * @param prefix 临时文件名的前缀
     * @param suffix 临时文件名的后缀
     * @return 临时文件创建成功返回true，否则返回false
     */
    public static String createTempFile(String prefix, String suffix) {
        try {
            File tempFile = File.createTempFile(prefix, suffix);
            return tempFile.getCanonicalPath();
        } catch (IOException e) {
            logger.error("创建临时文件失败", e.getMessage());
        }
        return null;
    }
}
