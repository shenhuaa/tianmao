package com.tianmao.app.util;

import javax.servlet.http.HttpServletRequest;


public final class HeadUtil {

    public static boolean isWeixin(HttpServletRequest request) {
        String agent = request.getHeader("User-Agent").toLowerCase();
        if (agent.indexOf("micromessenger") > -1) {
            return true;
        }
        return false;
    }

    public static boolean isAli(HttpServletRequest request) {
        String agent = request.getHeader("User-Agent").toLowerCase();
        if (agent.indexOf("alipayclient") > -1) {
            return true;
        }
        return false;
    }
}
