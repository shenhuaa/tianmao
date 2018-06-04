package com.tianmao.web.app.util;

import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证工具类
 *
 * @author roach
 * @date 2017/11/27
 */
public final class ValidateUtil {

    private static final String MOBILE = "^(0|86|17951)?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[57])[0-9]{8}$";

    private static final String EMAIL = "^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";

    private static final String DATE = "^(?=\\d)(?:(?!(?:1582(?:\\.|-|\\/)10(?:\\.|-|\\/)(?:0?[5-9]|1[0-4]))|(?:1752(?:\\.|-|\\/)0?9(?:\\.|-|\\/)(?:0?[3-9]|1[0-3])))(?=(?:(?!000[04]|(?:(?:1[^0-6]|[2468][^048]|[3579][^26])00))(?:(?:\\d\\d)(?:[02468][048]|[13579][26]))\\D0?2\\D29)|(?:\\d{4}\\D(?!(?:0?[2469]|11)\\D31)(?!0?2(?:\\.|-|\\/)(?:29|30))))(\\d{4})([-\\/.])(0?\\d|1[012])\\2((?!00)[012]?\\d|3[01])(?:$|(?=\\x20\\d)\\x20))?((?:(?:0?[1-9]|1[012])(?::[0-5]\\d){0,2}(?:\\x20[aApP][mM]))|(?:[01]\\d|2[0-3])(?::[0-5]\\d){1,2})?$";

    private ValidateUtil(){

    }

    /**
     * 验证手机号是否有效
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(final String mobile) {
        if (!StringUtils.hasText(mobile)) return false;

        Pattern p = Pattern.compile(MOBILE);
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    /**
     * 验证登陆用户名是否是邮箱号
     *
     * @param username
     * @return
     */
    public static boolean isEmail(final String username) {
        if (!StringUtils.hasText(username)) return false;

        Pattern p = Pattern.compile(EMAIL);
        Matcher m = p.matcher(username);
        return m.matches();
    }

    public static boolean isDate(final String date) {
        if (!StringUtils.hasText(date)) return false;

        Pattern p = Pattern.compile(DATE);
        Matcher m = p.matcher(date);
        return m.matches();
    }

    public static void main(String[] args) {

        Pattern regex = Pattern.compile(EMAIL);
        Matcher matcher = regex.matcher("602145116@qq.com");
        boolean isMatched = matcher.matches();
        System.out.println(isMatched);


        Pattern bRegex = Pattern.compile(MOBILE);
        Matcher BMatcher = bRegex.matcher("13642805124");
        boolean isMobile = BMatcher.matches();
        System.out.println(isMobile);

        String date = "2016-12-09 10:10:10";
        System.out.println("date = " + isDate(date));

        Calendar cal = Calendar.getInstance();
        TimeZone timeZone = cal.getTimeZone();
        System.out.println(timeZone.getID());
        System.out.println(timeZone.getDisplayName());

    }
}
