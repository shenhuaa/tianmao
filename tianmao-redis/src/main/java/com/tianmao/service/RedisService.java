package com.tianmao.service;
import java.util.Map;
import java.util.Set;

/**
 * redis 服务类
 * <p>
 * Created by roach on 2017/6/8.
 */
public interface RedisService {

    /**
     * 通过key删除
     *
     * @param keys
     */
    long del(final String... keys);

    /**
     * 模糊删除
     *
     * @param key
     */
    void delLick(final String key);

    <T> long set(final String key, T t);

    <T> long set(final String key, final T t, final long expire);

    <T> long hset(final String key, final String field, final T t);

    <T> long hset(final String key, final String field, final T t, final long expire);

    <T> T hget(final String key, final String field);

    boolean hdel(final String key, final String field);

    <HK, HV> Map<HK, HV> hgetAll(final String key);

    /**
     * 设置过期时间
     *
     * @param key
     * @param expire
     * @return
     */
    boolean expire(final String key, final long expire);

    boolean expire(final String prefix, final String key, final long expire);

    /**
     * 获取redis value (String)
     *
     * @param key
     * @return
     */
    <T> T get(final String key);

    <T> T get(final String key, final long expire);

    /**
     * 向list头部添加一条记录
     *
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    <T> long lPush(final String key, final T t);

    /**
     * 向list尾部添加一条记录
     *
     * @param key
     * @param t
     * @param <T>
     * @return
     */
    <T> long rPush(final String key, final T t);

    /**
     * 将list中的第一条记录移出
     *
     * @param key
     * @param <T>
     * @return
     */
    <T> T lPop(final String key);

    /**
     * 检查key是否已经存在
     *
     * @param key
     * @return
     */
    boolean exists(final String key);

    boolean exists(final String prefix, final String key);

    /**
     * 根据前缀获取所有
     *
     * @param pattern
     * @param <T>
     * @return
     */
    <T> Set<T> keys(final String pattern);

    /**
     * 清空redis 所有数据
     *
     * @return
     */
    String flushDB();

    /**
     * 查看redis里有多少数据
     */
    long dbSize();

    /**
     * 检查是否连接成功
     *
     * @return
     */
    String ping();

}
