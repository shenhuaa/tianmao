package com.tianmao.util;

import com.tianmao.config.AppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 水印图片工具类， 图片水印，文字水印，缩放，补白等
 *
 * @author roach
 * @date 2017/12/9
 */
public final class WatermarkImageUtil {

    private static final Logger logger = LoggerFactory.getLogger(WatermarkImageUtil.class);

    /**
     * 水印图片路径（wancheleyuan-web-support/resources目录下）
     */
    private static final String WATERMARK_PATH = "static/watermark.png";

    /**
     * 视频图片水印图（wancheleyuan-web-support/resources目录下）
     */
    private static final String PLAY_WATERMARK_PATH = "static/play_watermark.png";

    /**
     * 水印图片格式
     */
    private static final String WATERMARK_FORMAT = "png";

    private WatermarkImageUtil() {

    }

    /**
     * 添加图片水印
     *
     * @param targetImg 要写水印图片文件路径（写好水印会直接覆盖这张图片）
     */
    public final static void writeWatermark(String targetImg) {
        pressImage(targetImg, waterFile(AppContext.watermarkPath), false, 0, 0, 0.8f);
    }

    /**
     * 添加视频图片水印图
     *
     * @param targetImg
     */
    public final static void writePlayWatermark(String targetImg) {
        pressImage(targetImg, waterFile(AppContext.playWatermarkPath), true, -1, -1, 0.8f);
    }

    /**
     * 添加图片水印
     *
     * @param targetImg 目标图片路径，如：d:/a.png
     * @param waterFile 水印图片
     * @param x         水印图片距离目标图片左侧的偏移量，如果x < 0, 则在正中间
     * @param y         水印图片距离目标图片上侧的偏移量，如果y < 0, 则在正中间
     * @param alpha     透明度(0.0 -- 1.0, 0为完全透明，1 为完全不透明)
     */
    private final static void pressImage(String targetImg, File waterFile, boolean center, int x, int y, float alpha) {
        try {
            File file = new File(targetImg);
            Image image = ImageIO.read(file);
            if (image == null) return;

            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);

            Image waterImage = ImageIO.read(waterFile);    // 水印文件
            int width_1 = waterImage.getWidth(null);
            int height_1 = waterImage.getHeight(null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            int widthDiff = width - width_1;
            int heightDiff = height - height_1;
            if (x < 0) {
                x = widthDiff / 2;
            } else if (x > widthDiff) {
                x = widthDiff;
            }
            if (y < 0) {
                y = heightDiff / 2;
            } else if (y > heightDiff) {
                y = heightDiff;
            }
            width = width - width_1 - 5;
            height = height - height_1 - 5;
            if (center) {
                g.drawImage(waterImage, x, y, width_1, height_1, null);
            } else {
                g.drawImage(waterImage, width, height, width_1, height_1, null);
            }
            g.dispose();
            ImageIO.write(bufferedImage, WATERMARK_FORMAT, file);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 添加文字水印
     *
     * @param targetImg 目标图片路径，如：C://myPictrue//1.jpg
     * @param pressText 水印文字， 如：中国证券网
     * @param fontName  字体名称，    如：宋体
     * @param fontStyle 字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
     * @param fontSize  字体大小，单位为像素
     * @param color     字体颜色
     * @param x         水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param y         水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param alpha     透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
     */
    public static void pressText(String targetImg, String pressText, String fontName, int fontStyle, int fontSize, Color color, int x, int y, float alpha) {
        try {
            File file = new File(targetImg);

            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setColor(color);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

            int width_1 = fontSize * getLength(pressText);
            int height_1 = fontSize;
            int widthDiff = width - width_1;
            int heightDiff = height - height_1;
            if (x < 0) {
                x = widthDiff / 2;
            } else if (x > widthDiff) {
                x = widthDiff;
            }
            if (y < 0) {
                y = heightDiff / 2;
            } else if (y > heightDiff) {
                y = heightDiff;
            }

            g.drawString(pressText, x, y + height_1);
            g.dispose();
            ImageIO.write(bufferedImage, WATERMARK_FORMAT, file);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
     *
     * @param text
     * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
     */
    private static int getLength(String text) {
        int textLength = text.length();
        int length = textLength;
        for (int i = 0; i < textLength; i++) {
            if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
                length++;
            }
        }
        return (length % 2 == 0) ? length / 2 : length / 2 + 1;
    }

    /**
     * 图片缩放
     *
     * @param filePath 图片路径
     * @param height   高度
     * @param width    宽度
     * @param bb       比例不对时是否需要补白
     */
    public static void resize(String filePath, int height, int width, boolean bb) {
        try {
            double ratio = 0; //缩放比例    
            File f = new File(filePath);
            BufferedImage bi = ImageIO.read(f);
            Image itemp = bi.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
            //计算比例   
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                if (bi.getHeight() > bi.getWidth()) {
                    ratio = (new Integer(height)).doubleValue() / bi.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
                }
                AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bi, null);
            }
            if (bb) {
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null)) {
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
                } else {
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);
                    g.dispose();
                    itemp = image;
                }
            }
            ImageIO.write((BufferedImage) itemp, WATERMARK_FORMAT, f);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 获取水印图片
     *
     * @param imgPath 水印图路径
     * @return
     */
    private static File waterFile(String imgPath) {
        File file = new File(imgPath);
        if (!file.exists()) {
            logger.error("水印图不存在:[{}]", imgPath);
        }
        return file;
    }


    public static void main(String[] args) throws IOException {
        //getFile();
        writePlayWatermark("d:/1.png");
    }

}