package com.tianmao.app.util.redis;

public class Redis {
    /**
     * 保存用户对应的token
     */
    public static final String TOKEN_USER = "token:user:";
    /**
     * 保存token对应的用户id
     */
    public static final String USER_TOKEN = "user:token:";
    public static final String USER_MESSAGE = "user:msg:";
    public static final String USER_MESSAGE_PWD = "user:msg:pwd:";

    /**
     * token 过期时间（30天）
     */
    public static final long TOKEN_TIMEOUT = 24 * 30 * 3600;
}