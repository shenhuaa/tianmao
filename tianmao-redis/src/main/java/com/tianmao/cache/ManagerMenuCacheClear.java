package com.tianmao.cache;

import org.springframework.cache.annotation.CacheEvict;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 清空菜单缓存
 * <p/>
 * Created by roach on 16/7/27.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@CacheEvict(cacheNames = {
        "cache:manager_menu:get_list",
        "cache:manager_menu:get_list_by_uid",
        "cache:manager_menu:get"}, allEntries = true)
public @interface ManagerMenuCacheClear {
}