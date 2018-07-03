package com.tianmao.util;
import com.tianmao.config.AppContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 文件前缀显示处理
 *
 * @author roach
 * @date 2017/09/06
 */
@Component
public final class FilePrefixUrlUtil {

    private FilePrefixUrlUtil() {

    }

    /**
     * 获取新的url
     *
     * @param url
     * @return
     */
    public static String getNewImgUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            return AppContext.imageUrl + AppContext.appDefaultImg;
        } else if (url.contains("http://") || url.contains("https://")) {
            return url;
        }
        return AppContext.imageUrl + url;
    }

    /**
     * 获取新的url
     *
     * @param url
     * @return
     */
    public static String getNewUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            return AppContext.imageUrl + AppContext.appDefaultImg + AppContext.imgSize;
        } else if (url.contains("http://") || url.contains("https://")) {
            return url;
        }
        return AppContext.imageUrl + url + AppContext.imgSize;
    }
}
