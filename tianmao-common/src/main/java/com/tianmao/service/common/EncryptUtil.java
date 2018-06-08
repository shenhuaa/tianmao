package com.tianmao.service.common;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;


/**
 * @author CM
 * @ClassName: EncryptUtil
 * @Description: 加密工具包
 * @date 2015年7月2日 上午10:07:48
 * @since version 1.0
 */
public class EncryptUtil {

    /**
     * encrypt the input type by MD5
     *
     * @param input input string
     * @return md5 encrypted string
     */
    public static String md5(String input) {
        try {
            return DigestUtils.md5Hex(input.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * encrypt the input type by SHA256
     *
     * @param input input string
     * @return SHA256 encrypted string
     */
    public static String sha256(String input) {
        return DigestUtils.sha256Hex(input.getBytes());
    }

    /**
     * encrypt the input type by SHA384
     *
     * @param input input string
     * @return SHA384 encrypted string
     */
    public static String sha384(String input) {
        return DigestUtils.sha384Hex(input.getBytes());
    }

    /**
     * encrypt the input type by sha1
     *
     * @param input input string
     * @return sha1 encrypted string
     */
    public static String sha1(String input) {
        return DigestUtils.sha1Hex(input);

    }


    /**
     * encrypt the input type by SHA512
     *
     * @param input input string
     * @return SHA512 encrypted string
     */
    public static String sha512(String input) {
        return DigestUtils.sha512Hex(input.getBytes());
    }

    public static void main(String[] args) {
        System.out.println(md5("123456lottery@alpha").toUpperCase());
    }
}
