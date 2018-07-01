package com.tianmao.service.exception;

import com.tianmao.service.common.HttpCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * BaseServiceException捕获异常类
 *
 * @author roach
 * @date 2017/11/29
 */
@ControllerAdvice
public class TryCatchBaseServiceExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(TryCatchBaseServiceExceptionHandler.class);
    @ExceptionHandler(value = BaseServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseServiceException defaultErrorHandler(HttpServletRequest request, Exception e) {
        String message = e.getMessage();
        int index = message.indexOf(":");
        HttpCode httpCode;
        if (index > 0) {
            String code = message.substring(0, index);
            httpCode = HttpCode.valueOf(code);
            message = message.substring(index + 1, message.length());
        } else {
            httpCode = HttpCode.valueOf(message);
            message = httpCode.getMessage();
        }
        logger.warn("[ 请求路径:{},状态码:{},{}]", request.getRequestURL(), httpCode.getCode(), message);
        return new BaseServiceException(httpCode);
    }
}