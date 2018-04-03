package tianmao.web.app.util.upload;

import okhttp3.*;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import tianmao.common.HttpCode;
import tianmao.service.exception.BaseServiceException;
import tianmao.web.app.config.AppContext;
import tianmao.web.app.util.VideoThumbnailUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * 文件上传工具类
 *
 * @author roach
 * @date 2017/09/06
 */
public final class FileUploadUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);

    private FileUploadUtil() {

    }

    /**
     * base64文件上传,只支持图片格式
     *
     * @param base64      base64码
     * @param storagePath 存储路径
     * @param extension   后缀名
     * @return
     */
    public static String upload(String base64, String storagePath, String extension) {
        int index = base64.indexOf(",");
        base64 = base64.substring(index + 1, base64.length());
        Assert.notNull(base64, "base64不能为空");
        Assert.notNull(storagePath, "文件保存路径不能为空对象不能为空");
        Assert.notNull(extension, "后缀名不能为空");

        String saveStoragePath = getSaveStoragePath(storagePath, extension);
        generateImage(base64, AppContext.storagePrefix + saveStoragePath);
        return saveStoragePath;
    }

    /**
     * 文件上传
     *
     * @param file        文件对象
     * @param storagePath 存储路径
     * @return
     */
    public static String upload(MultipartFile file, String storagePath) {
        Assert.notNull(file, "文件对象不能为空");
        Assert.notNull(storagePath, "文件保存路径不能为空对象不能为空");

        String originalFilename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        if (StringUtils.isEmpty(extension)) {
            throw new BaseServiceException(HttpCode.ILLEGAL_PARAMETERS, "文件后缀名不存在");
        }
        if (file.getSize() == 0) {
            throw new BaseServiceException(HttpCode.ILLEGAL_PARAMETERS, "该图片为空!");
        }
        String saveStoragePath = getSaveStoragePath(storagePath, extension);
        File targetFile = new File(AppContext.storagePrefix + saveStoragePath);
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            logger.error("文件保存失败", e);
            throw new BaseServiceException(HttpCode.ERROR, "文件保存失败");
        }
        String contentType = file.getContentType();
        if (contentType.contains("image")) {
            //图片水印
            //WatermarkImageUtil.writeWatermark(targetFile.toString());
        } else if (contentType.contains("video")) {
            //视频缩略图
            String videoThumbnail = VideoThumbnailUtil.processImg(targetFile.toString());
                /*if (!StringUtils.isEmpty(videoThumbnail)) {
                    WatermarkImageUtil.writePlayWatermark(videoThumbnail);
                }*/
        }
        //Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(0.1f).toFile(targetFile);
        return saveStoragePath;
    }

    /**
     * 获取保存路径
     *
     * @param storagePath
     * @return
     */
    private static String getSaveStoragePath(String storagePath, String extension) {
        StringBuilder saveFilePath = new StringBuilder(storagePath + "/");

        //按日期生成目录名称
        Calendar date = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        saveFilePath.append(sdf.format(date.getTime()));//目录名称
        saveFilePath.append("/");
        File fileDir = new File(AppContext.storagePrefix + saveFilePath.toString());
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        //生成的文件名称
        saveFilePath.append(UUID.randomUUID().toString().replace("-", ""));
        saveFilePath.append(".");
        saveFilePath.append(extension);

        return saveFilePath.toString();
    }

    /**
     * 生成图片
     *
     * @param base64
     * @param storagePath
     * @return
     */
    private static boolean generateImage(String base64, String storagePath) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(base64);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(storagePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            logger.error("文件保存失败", e);
            throw new BaseServiceException(HttpCode.ERROR, "文件保存失败");
        }
    }

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        //一种：参数请求体
        FormBody signBody = new FormBody.Builder()
                .add("sign", "D3230911CD08316A8A88D8DE69AF")
                .build();

        FormBody timestampBody = new FormBody.Builder()
                .add("timestamp", "1510557559138")
                .build();


        //二种：文件请求体
        MediaType type = MediaType.parse("application/octet-stream");//"text/xml;charset=utf-8"
        File file = new File("d:/a.png");
        RequestBody fileBody = RequestBody.create(type, file);


        //三种：混合参数和文件请求
        RequestBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.ALTERNATIVE)
                //一样的效果
                .addPart(Headers.of("Content-Disposition", "form-data; name=\"sign\""), signBody)
                .addFormDataPart("sign2", "1111111111")
                .addPart(Headers.of("Content-Disposition", "form-data; name=\"timestamp\""), timestampBody)
                .addPart(Headers.of("Content-Disposition", "form-data; name=\"file\"; filename=\"a.png\""), fileBody)
                .build();


        Request request = new Request.Builder().url("http://127.0.0.1:8090/common/uploadImg")
                //  Request request = new Request.Builder().url("http://127.0.0.1:8080/advert/upload")
                .addHeader("User-Agent", "android")
                .header("Content-Type", "text/html; charset=utf-8;")
                .post(multipartBody)//传参数、文件或者混合，改一下就行请求体就行
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("response = " + response.message());
            }
        });
    }

}
