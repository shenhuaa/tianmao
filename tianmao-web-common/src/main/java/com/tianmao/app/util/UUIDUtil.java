package com.tianmao.app.util;
import java.util.UUID;

/**
 * 生成UUID
 *
 * @author roach
 * @date 2017/11/27
 */
public final class UUIDUtil {

    private UUIDUtil() {

    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println("UUIDUtil.getUUID() = " + UUIDUtil.getUUID());
    }
}
