package com.tianmao.service.common;
import java.util.Random;


/**
 * @author chenming
 * @ClassName: RandomUtil
 * @Description: 随机工具类
 * @date 2017年8月30日 下午3:15:52
 */
public class RandomUtil {
    public static int getRandomInt(int min, int max) {
        Random random = new Random();
        int value = min + random.nextInt((max - min));
        return value;
    }

    //生成TOKEN
    public static String buildToken() {
        String password = DateUtil.getNowTime("yyyyMMddHHmmss");
        Random r = new Random();
        while (password.length() < 20) {
            String pr = r.nextInt(10000) + "";
            password += pr;
        }
        password = password.substring(0, 18).toUpperCase();
        return EncryptUtil.md5(password).toUpperCase();
    }

    public static void main(String[] args) {
        for (int x = 0; x <= 100; x++) {
            System.out.println(" getRandomInt(1, 10); = " + getRandomInt(1, 10));
        }
    }
}
