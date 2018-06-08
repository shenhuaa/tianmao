package com.tianmao.service.exception;

import com.tianmao.service.common.HttpCode;
import lombok.Builder;
import lombok.Data;

/**
 * service自定义异常，异常是需要用户自己处理的
 *
 * @author roach
 * @date 2017/11/29
 */
@Builder
@Data
public class BaseServiceException extends RuntimeException {

    private HttpCode httpCode;
    private String message;

    public BaseServiceException(HttpCode httpCode) {
        super(String.valueOf(httpCode));
        this.message = String.valueOf(httpCode);
    }

    public BaseServiceException(HttpCode httpCode, String message) {
        super(String.valueOf(httpCode) + ":" + message);
        this.message = String.valueOf(httpCode) + ":" + message;
    }

}
