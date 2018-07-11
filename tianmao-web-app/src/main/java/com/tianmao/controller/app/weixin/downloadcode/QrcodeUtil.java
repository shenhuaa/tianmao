package com.tianmao.controller.app.weixin.downloadcode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianmao.app.util.Qrcode;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Component
public class QrcodeUtil {

    private static final Logger logger = LoggerFactory.getLogger(QrcodeUtil.class);


    /**
     * 永久公众号二维码
     * @param accessToken
     * @return
     * @throws Exception
     */
    public Qrcode getAlwaysQrcode(String accessToken) throws Exception {
        Assert.hasText(accessToken, "accessToken不能为空");
        URI requestUri = this.getRequestUri(accessToken);
        HttpPost httpPost = new HttpPost(requestUri);
        Map<String, Object> dataMap = new HashMap();
        dataMap.put("action_name", "QR_LIMIT_STR_SCENE");
        Map<String, Object> actionInfoMap = new HashMap();
        actionInfoMap.put("scene", new Scene("sdfsdfss888"));
        dataMap.put("action_info", actionInfoMap);
        String json = "";

        try {
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(dataMap);
        } catch (JsonProcessingException var41) {
            logger.error("json格式错误：", var41);
            return null;
        }

        httpPost.setEntity(new StringEntity(json, "utf-8"));

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            Throwable var9 = null;

            Qrcode var13;
            try {
                CloseableHttpResponse response = httpClient.execute(httpPost);
                Throwable var11 = null;

                try {
                    Map map = this.getResponseBody(response);
                    var13 = Qrcode.builder().ticket((String)map.get("ticket")).url((String)map.get("url")).build();
                } catch (Throwable var40) {
                    var11 = var40;
                    throw var40;
                } finally {
                    if (response != null) {
                        if (var11 != null) {
                            try {
                                response.close();
                            } catch (Throwable var39) {
                                var11.addSuppressed(var39);
                            }
                        } else {
                            response.close();
                        }
                    }

                }
            } catch (Throwable var43) {
                var9 = var43;
                throw var43;
            } finally {
                if (httpClient != null) {
                    if (var9 != null) {
                        try {
                            httpClient.close();
                        } catch (Throwable var38) {
                            var9.addSuppressed(var38);
                        }
                    } else {
                        httpClient.close();
                    }
                }

            }

            return var13;
        } catch (IOException var45) {
            logger.error("获取 ACCESS_TOKEN失败：", var45);
            return null;
        }
    }

    /**
     * 临时公众号二维码
     * @param sceneStr
     * @param accessToken
     * @param expireSeconds
     * @return
     * @throws Exception
     */
    public Qrcode getInterimQrcode(String sceneStr, String accessToken, long expireSeconds) throws Exception {
        Assert.hasText(sceneStr, "场景值ID不能为空");
        Assert.hasText(accessToken, "accessToken不能为空");
        Assert.isTrue(expireSeconds > 0L, "expireSeconds必须大于0");
        URI requestUri = this.getRequestUri(accessToken);
        HttpPost httpPost = new HttpPost(requestUri);
        Map<String, Object> dataMap = new HashMap();
        dataMap.put("action_name", "QR_STR_SCENE");
        dataMap.put("expire_seconds", expireSeconds);
        Map<String, Object> actionInfoMap = new HashMap();
        actionInfoMap.put("scene", new Scene(sceneStr));
        dataMap.put("action_info", actionInfoMap);
        String json = "";

        try {
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(dataMap);
        } catch (JsonProcessingException var43) {
            logger.error("json格式错误：", var43);
            return null;
        }

        httpPost.setEntity(new StringEntity(json, "utf-8"));

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            Throwable var11 = null;

            Qrcode var15;
            try {
                CloseableHttpResponse response = httpClient.execute(httpPost);
                Throwable var13 = null;

                try {
                    Map map = this.getResponseBody(response);
                    var15 = Qrcode.builder().ticket((String)map.get("ticket")).url((String)map.get("url")).build();
                } catch (Throwable var42) {
                    var13 = var42;
                    throw var42;
                } finally {
                    if (response != null) {
                        if (var13 != null) {
                            try {
                                response.close();
                            } catch (Throwable var41) {
                                var13.addSuppressed(var41);
                            }
                        } else {
                            response.close();
                        }
                    }

                }
            } catch (Throwable var45) {
                var11 = var45;
                throw var45;
            } finally {
                if (httpClient != null) {
                    if (var11 != null) {
                        try {
                            httpClient.close();
                        } catch (Throwable var40) {
                            var11.addSuppressed(var40);
                        }
                    } else {
                        httpClient.close();
                    }
                }

            }

            return var15;
        } catch (IOException var47) {
            logger.error("获取 ACCESS_TOKEN失败：", var47);
            return null;
        }
    }

    private URI getRequestUri(String accessToken) throws Exception {
        try {
            return (new URIBuilder()).setScheme("https").setHost("api.weixin.qq.com").setPath("/cgi-bin/qrcode/create").setParameter("access_token", accessToken).build();
        } catch (URISyntaxException var3) {
            throw new Exception("不合法的参数:" + var3.getMessage());
        }
    }

    private Map getResponseBody(CloseableHttpResponse response) throws IOException, Exception {
        HttpEntity responseEntity = response.getEntity();
        String responseString = EntityUtils.toString(responseEntity, "utf-8");
        Map map = (Map)(new ObjectMapper()).readValue(responseString, Map.class);
        if (null != map.get("errcode")) {
            throw new Exception("获取二维码失败:" + map.get("errmsg") + ",错误代码:" + map.get("errcode"));
        } else {
            return map;
        }
    }
}
