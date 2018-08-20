package com.tianmao.app.util;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 文件信息
 *
 * @author liwenjie
 * @date 2018/8/18
 */
@Builder
@Data
public class MyFileInfo implements Serializable {

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

}