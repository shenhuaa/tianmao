package com.tianmao.service.common.serviceImpl;

import com.tianmao.service.common.BaseService;
import com.tianmao.service.mybatis.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * 基础服务类
 * @author roach
 * @date 2018/6/18
 */
public class BaseServiceImpl<ID, T> implements BaseService<ID, T> {

    @Autowired
    private BaseMapper<T> baseMapper;

    @Override
    public T insertSelective(T t) {
        baseMapper.insertSelective(t);
        return t;
    }

    @Override
    public T updateByPrimaryKeySelective(T t) {
        baseMapper.updateByPrimaryKeySelective(t);
        return t;
    }

    @Override
    public T selectByPrimaryKey(ID id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public T selectOne(T t) {
        return baseMapper.selectOne(t);
    }

    @Override
    public List<T> select(T t) {
        return baseMapper.select(t);
    }

    @Override
    public List<T> selectAll(T t) {
        return baseMapper.selectAll();
    }

}
