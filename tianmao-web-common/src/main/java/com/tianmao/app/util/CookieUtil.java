package com.tianmao.app.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie
 *
 * @author roach
 * @date 2018/1/3
 */
public final class CookieUtil {

    private CookieUtil() {
    }
    public static String getCookie(HttpServletRequest request, String key){
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(key)){
                    return cookie.getValue();
                }
            }
        }
        return request.getParameter(key);
    }

    public static void setCookie(HttpServletResponse response, String key, String value){
        Cookie userCookie=new Cookie(key,value);
        userCookie.setMaxAge(-1);
        userCookie.setPath("/");
        response.addCookie(userCookie);
    }
}
