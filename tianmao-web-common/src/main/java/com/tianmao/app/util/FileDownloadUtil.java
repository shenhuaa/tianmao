package com.tianmao.app.util;

import com.tianmao.app.config.AppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * 文件下载工具类
 *
 * @author roach
 * @date 2018/1/17
 */
public final class FileDownloadUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);

    private FileDownloadUtil() {

    }

    /**
     * 单个文件下载
     *
     * @param fileName 下载后的文件名
     * @param filePath 下载后路径
     * @param response
     */
    public static ResponseEntity<byte[]> download(String fileName, String filePath, HttpServletResponse response) {
        try {

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            Path path = Paths.get(filePath);
            Files.copy(path, response.getOutputStream());
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ResponseEntity(INTERNAL_SERVER_ERROR);
    }

    /**
     * 远程文件下载
     *
     * @param remoteFilePath 远程文件路径
     * @param storagePath    存储路径
     * @param storagePath    文件后缀名
     */
    public static FileInfo remoteDownload(String remoteFilePath, String storagePath, String extension) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        String saveStoragePath = FileUploadUtil.getSaveStoragePath(storagePath, extension);

        //本地存储路径
        File file = new File(AppContext.storagePrefix + saveStoragePath);
        try {
            URL url = new URL(remoteFilePath);
            HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream(file));
            int len = 2048;
            byte[] b = new byte[len];
            while ((len = bis.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            bos.flush();
            bis.close();
            httpUrl.disconnect();
            return new FileInfo(saveStoragePath, file.length());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return new FileInfo();
    }

}