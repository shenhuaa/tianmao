package com.tianmao.app.util;

import com.tianmao.utils.ServletRequestAttributesUtil;
import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;

/**
 * 设备工具类
 *
 * @author roach
 * @date 2018/1/26
 */
public final class DeviceTypeUtil {
    private DeviceTypeUtil() {

    }
    /**
     * 判断是否为手机端
     *
     * @return
     */
    public static boolean isMobile() {
        HttpServletRequest request = ServletRequestAttributesUtil.getHttpServletRequest();
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        DeviceType deviceType = operatingSystem.getDeviceType();
        if (DeviceType.MOBILE == deviceType) {
            return true;
        }
        return false;
    }
}