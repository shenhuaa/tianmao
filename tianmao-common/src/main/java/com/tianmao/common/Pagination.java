package com.tianmao.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * ant design
 *
 * @author roach
 * @date 2018-3-9
 */
@Data
@AllArgsConstructor
public class Pagination implements Serializable {

    /**
     * 当前页
     */
    private int current;

    /**
     * 条数
     */
    private int pageSize;

    /**
     * 总数量
     */
    private long total;
}

