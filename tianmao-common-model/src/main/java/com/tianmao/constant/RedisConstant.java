package com.tianmao.constant;

/**
 * Created by cm on 2017/6/22.
 */
public interface RedisConstant {

    String DICT_LIST = "dict:list";
    String DICT = "dict:";

    String SHOP_CACHE_LOCK = "SHOP_CACHE_LOCK";

    long SHOP_CACHE_LOCK_EXPIRE = 20;

    String VENDOR_CACHE_LOCK = "VENDOR_CACHE_LOCK";

    long VENDOR_CACHE_LOCK_EXPIRE = 60 * 60;

    String SHOP_STATUS = "SHOP_STATUS_";

    String VENDER_GOODS = "VENDER_GOODS_";

    String VENDER_ORDER = "VENDER_ORDER_";

    String VENDOR_HEARTBEAT_="VENDOR_HEARTBEAT_";

    String VENDOR_HEARTBEAT_TIME="VENDOR_HEARTBEAT_TIME_";

    long VENDER_GOODS_EXPIRE = 5 * 60;

    long VENDER_ORDER_EXPIRE = 60 * 60;

    long VENDOR_HEARTBEAT_TIME_EXPIRE = 18;

    long SHOP_STATUS_EXPIRE = 90;

}
