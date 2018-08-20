package com.tianmao.app.util;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


/**
 * 文件下载工具类
 *
 * @author liwenjie
 * @date 2018/8/18
 */
public final class FileDownloadUtils {

    private static final Logger logger = LogManager.getLogger(FileDownloadUtils.class);

    private FileDownloadUtils() {

    }

    /**
     * 单个文件下载
     *
     * @param saveFileName 下载后的文件名
     * @param filePath     文件所在的路径
     */
    public static ResponseEntity<byte[]> download(String saveFileName, String filePath) {
        try {
            HttpServletResponse response = ServletRequestAttributesUtils.getHttpServletResponse();
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "filename=" + URLEncoder.encode(saveFileName, "UTF-8"));
            Path path = Paths.get(filePath);
            Files.copy(path, response.getOutputStream());
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
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
     * 多文件下载
     *
     * @param saveFileName 压缩包名称(如:temp.zip)
     * @param files        文件集合
     * @return
     */
    public static ResponseEntity<byte[]> multiFileDownload(String saveFileName, List<? extends MyFileInfo> files) {
        if (files == null || files.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (files.size() == 1) {
            try {
                MyFileInfo file = files.get(0);
                String fileName = URLEncoder.encode(file.getFileName(), "UTF-8");
                return FileDownloadUtils.download(fileName, file.getFilePath());
            } catch (UnsupportedEncodingException e) {
                logger.error(e);
            }
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        File tempDir = null;
        ZipOutputStream zipOut = null;
        try {
            // 临时文件
            tempDir = Files.createTempDirectory(null).toFile();

            // 压缩后的文件
            File zipFile = new File(tempDir, saveFileName);
            zipOut = new ZipOutputStream(new FileOutputStream(zipFile));

            // 保存所有文件名称
            List<String> fileNames = new ArrayList<>();
            for (MyFileInfo file : files) {
                File timeFile = new File(file.getFilePath());
                if (!timeFile.exists()) {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
                FileInputStream stream = new FileInputStream(timeFile);
                String fileName = getFileName(fileNames, file.getFileName());
                fileNames.add(fileName);
                zipOut.putNextEntry(new ZipEntry(fileName));
                byte[] buffer = new byte[1024];
                int len;
                while ((len = stream.read(buffer)) != -1) {
                    zipOut.write(buffer, 0, len);
                }
                if (stream != null) {
                    stream.close();
                }
            }
            zipOut.finish();
            return FileDownloadUtils.download(saveFileName, zipFile.getPath());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (zipOut != null) {
                    zipOut.close();
                }
                FileUtils.deleteDirectory(tempDir);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    /**
     * 获取唯一的文件名称
     *
     * @param fileNames
     * @param fileName
     * @return
     */
    private static String getFileName(List<String> fileNames, String fileName) {
        if (fileName == null || fileName.length() == 0) {
            return fileName;
        }
        int index = fileName.lastIndexOf('.');
        String subName = fileName.substring(0, index);
        String suffix = fileName.substring(index, fileName.length());
        if (fileNames.contains(fileName)) {
            fileName = subName + "-" + getRandom() + suffix;
            return getFileName(fileNames, fileName);
        }
        return fileName;
    }

    // 生成随机数
    private static String getRandom() {
        return String.valueOf(new Random().nextInt(8) + 1);
    }

}

