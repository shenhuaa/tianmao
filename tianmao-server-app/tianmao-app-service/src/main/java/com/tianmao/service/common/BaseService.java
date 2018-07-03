package com.tianmao.service.common;

import java.util.List;

/**
 * 基础服务类
 *
 * @author roach
 * @date 2018/6/18
 */
public interface BaseService<ID, T> {

    T insert(T t);

    T updateByPrimaryKey(T t);

    T updateByPrimaryKeySelective(T t);

    T selectByPrimaryKey(ID id);

    T selectOne(T t);

    List<T> select(T t);

    List<T> selectAll(T t);

}
