package tianmao.web.app.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import tianmao.common.EncryptUtil;
import tianmao.common.StringUtil;
import tianmao.web.app.constant.IConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

public abstract class APIParamUtil {
    static Logger logger = Logger.getLogger(APIParamUtil.class);

    /**
     * 把对象转化为json并out输出
     *
     * @param obj
     * @param response
     */
    public static void ToJSONString(Object obj, HttpServletResponse response) {
        PrintWriter out = null;
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain; charset=utf-8");
        response.setHeader("Content-type", MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            out = response.getWriter();
            String jsonText = JSON.toJSONString(obj, true);
            out = response.getWriter();
            response.setStatus(HttpStatus.SC_FORBIDDEN);
            out.print(jsonText);
        } catch (Exception e) {
            logger.info("出错!", e);
        } finally {
            if (out != null)
                out.close();
        }
    }

    public static void writer(JSONObject json, HttpServletResponse response) {
        if (json == null) {
            json = ErrorParam.buildJSON(1099);
        }
        Integer result = json.getInteger("result");
        if (result != null) {
            json.remove("result");
            if (result == 0) {
                json.put("HttpCode", 200);
            } else {
                json.put("HttpCode", result);
            }
        }
        PrintWriter out = null;
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain; charset=utf-8");
        try {
            out = response.getWriter();
            String obj = JSONObject.toJSONString(json,
                    SerializerFeature.PrettyFormat,
                    SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteNullStringAsEmpty,
                    SerializerFeature.WriteNullListAsEmpty,
                    SerializerFeature.MapSortField);
            out.print(obj);
        } catch (Exception e) {
            logger.info("出错!", e);
        } finally {
            if (out != null)
                out.close();
        }
    }


    /**
     * 签名验证
     *
     * @return
     */
    public static boolean sign(JSONObject json) {
        String sign = json.get("sign").toString();
        if (StringUtil.isBlank(sign)) {
            return false;
        }
        //按参数名称正序排列
        TreeMap<String, String> parameterMap = new TreeMap<String, String>();
        for (Entry<String, Object> entry : json.entrySet()) {
            parameterMap.put(entry.getKey(), entry.getValue().toString());
        }
        //拼接除sign外的所有参数（key+value方式）
        Iterator<Entry<String, String>> iterator2 = parameterMap.entrySet()
                .iterator();
        String allParameters = "";
        while (iterator2.hasNext()) {
            Entry<String, String> map = iterator2.next();
            if (!map.getKey().equals("sign")) {
                allParameters += map.getKey() + map.getValue();
            }
        }
        //拼接的参数前加上系统分配的appKey
        String encryptStr = IConstants.APP_KEY + allParameters;
        //将拼接的参数进行MD5加密并转为大写
        String digestStr = EncryptUtil.md5(encryptStr).toUpperCase();

        // 判断签名是否正确
        if (sign != null && digestStr.equals(sign)) {
            return true;
        }
        System.out.println(digestStr);
        return false;
    }

    public static JSONObject getParam(HttpServletRequest request) throws IOException {
        JSONObject json = new JSONObject();
        for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            json.put(entry.getKey(), entry.getValue()[0]);
        }
        return json;
    }

    public static void main(String[] args) {

    }
}
