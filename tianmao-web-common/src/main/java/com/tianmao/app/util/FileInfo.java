package com.tianmao.app.util;

import lombok.Data;

/**
 * 文件
 *
 * @author roach
 * @date 2018/6/5
 */
@Data
public class FileInfo {

    private String path;
    private Long size;

    public FileInfo() {

    }

    public FileInfo(String path, long size) {
        this.path = path;
        this.size = size;
    }
}
