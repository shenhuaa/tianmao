package com.tianmao.app.exception;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tianmao.service.exception.BaseServiceException;
import com.tianmao.utils.HttpCode;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Feign 异常处理
 *
 * @author roach
 * @date 2017/11/29
 */
public class FeignErrorDecoder implements ErrorDecoder {
    private static final Logger logger = LoggerFactory.getLogger(FeignErrorDecoder.class);

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            String body = Util.toString(response.body().asReader());
            Map map = new ObjectMapper().readValue(body, Map.class);
            String exception = (String) map.get("exception");
            String message = (String) map.get("message");
            if (exception.equals(BaseServiceException.class.getName())) {
                int index = message.indexOf(":");
                HttpCode httpCode;
                if (index > 0) {
                    httpCode = HttpCode.valueOf(message.substring(0, index));
                    message = message.substring(index + 1, message.length());
                } else {
                    httpCode = HttpCode.valueOf(message);
                    message = httpCode.getMessage();
                }
                return new BaseServiceException(httpCode, message);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return FeignException.errorStatus(methodKey, response);
    }
}