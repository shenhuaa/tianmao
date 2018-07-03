package com.tianmao.util;
import org.springframework.util.StringUtils;

/**
 * 数字工具类
 *
 * @author roach
 * @date 2018/1/7
 */
public final class NumericUtil {

    private NumericUtil() {

    }

    /**
     * 判断是否为数字
     *
     * @param charSequence
     * @return
     */
    public static boolean isNumeric(CharSequence charSequence) {
        if (!StringUtils.hasText(charSequence)) {
            return false;
        }

        int sz = charSequence.length();
        for (int i = 0; i < sz; ++i) {
            if (!Character.isDigit(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
