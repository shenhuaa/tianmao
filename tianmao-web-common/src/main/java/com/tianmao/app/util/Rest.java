package com.tianmao.app.util;

import com.tianmao.utils.HttpCode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Rest 对象,便捷的返回键值对给到前端.
 * 返回code: 200：成功   500:失败
 * <p>
 * Created by roach on 2017/10/20.
 */
public final class Rest extends LinkedHashMap<String, Object> {

    private static final long serialVersionUID = -6752752453160207691L;

    private Rest() {

    }

    private Rest(final Builder builder) {
        Map<String, Object> data = builder.data;
        HttpCode httpCode = builder.httpCode;
        String message = builder.message;
        if (message == null || message == "") {
            message = httpCode.getMessage();
        }
        put("code", httpCode.getCode());
        put("message", message);
        if (data.isEmpty()) {
            put("data", new HashMap<>());
        } else {
            put("data", data);
        }
    }

    /**
     * 创建构造器,随后可以自由构造参数,然后调用 build() 完成构建.
     *
     * @return Builder
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /**
     * 构建器.
     */
    public static class Builder {
        private final Map<String, Object> data = new HashMap<>();
        private HttpCode httpCode = HttpCode.ERROR;
        private String message;

        /**
         * 私有化构造器,目的是希望固有对象构建方式.
         */
        private Builder() {
        }

        /**
         * 在根目录下添加键值对.
         *
         * @param key key
         * @param val val
         * @return Builder
         */
        public Builder put(final String key, final Object val) {
            data.put(key, val);
            return this;
        }

        /**
         * 添加一批键值对数据
         *
         * @param map map
         * @return Builder
         */
        public Builder putAll(final Map<String, Object> map) {
            data.putAll(map);
            return this;
        }

        /**
         * 与前端协定的状态码.
         *
         * @param val 状态码数值
         * @return Builder
         */
        public Builder code(final HttpCode val) {
            httpCode = val;
            return this;
        }

        public Builder message(final String val) {
            this.message = val;
            return this;
        }

        /**
         * 与前端协定的状态码(0：成功   1:失败 )
         *
         * @param val 状态码:false = 0  true = 1
         * @return Builder
         */
        public Builder code(final boolean val) {
            httpCode = val ? HttpCode.OK : HttpCode.ERROR;
            return this;
        }

        /**
         * 完成对象构建.
         *
         * @return Rest
         */
        public Rest build() {
            return new Rest(this);
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "data=" + data +
                    ", httpCode=" + httpCode +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

}
