package tianmao.web.app.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import tianmao.common.HttpCode;
import tianmao.service.exception.BaseServiceException;
import tianmao.web.app.config.AppContext;
import tianmao.web.app.util.Rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 通用错误处理器.
 *
 * @author roach
 * @date 2018/2/16
 */
@ControllerAdvice
public class ExceptionHandlerController {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    /**
     * 500
     *
     * @param request
     * @param response
     * @param e
     * @throws Exception
     */
    @ExceptionHandler(Exception.class)
    public String serverError(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        String requestURI = request.getRequestURI();
        String message = "";
        HttpCode httpCode = HttpCode.ERROR;
        if (e instanceof BaseServiceException) {
            BaseServiceException baseServiceException = (BaseServiceException) e;
            String codeMessage = baseServiceException.getMessage();
            int index = codeMessage.indexOf(":");
            if (index > 0) {
                String code = codeMessage.substring(0, index);
                httpCode = HttpCode.valueOf(code);
                message = codeMessage.substring(index + 1, codeMessage.length());
            } else {
                httpCode = HttpCode.valueOf(codeMessage);
                message = httpCode.getMessage();
            }
            logger.warn("[{}，{}]", requestURI, message);
        } else if (e instanceof HttpMediaTypeNotAcceptableException) {
            HttpMediaTypeNotAcceptableException httpMediaTypeNotAcceptableException = (HttpMediaTypeNotAcceptableException) e;
            //如：produces = MediaType.IMAGE_JPEG_VALUE
            List<MediaType> parameterName = httpMediaTypeNotAcceptableException.getSupportedMediaTypes();
            if (parameterName != null && parameterName.size() > 0) {
                MediaType mediaType = parameterName.get(0);
                message = String.format("不支持[%s]请求媒体类型", mediaType);
            } else {
                message = "不支持请求媒体类型";
            }
            httpCode = HttpCode.MISSING_PARAMETERS;
            logger.warn("[{}，{}]", requestURI, message);
        } else if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException parameterException = (MissingServletRequestParameterException) e;
            String parameterName = parameterException.getParameterName();
            message = String.format("缺少[%s]参数", parameterName);
            httpCode = HttpCode.MISSING_PARAMETERS;
            logger.warn("[{}，{}]", requestURI, message);
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException mismatchException = (MethodArgumentTypeMismatchException) e;
            String name = mismatchException.getName();
            message = String.format("[%s]参数类型异常", name);
            httpCode = HttpCode.PARAMETER_TYPE_ERROR;
            logger.warn("[{}，{}]", requestURI, message);
        } else if (e instanceof IllegalArgumentException) {
            message = "参数异常";
            httpCode = HttpCode.PARAMETER_TYPE_ERROR;
            logger.warn("[{}，{}]", requestURI, message);
        } else if (e instanceof HttpMediaTypeNotSupportedException) {
            HttpMediaTypeNotSupportedException mediaTypeNotSupportedException = (HttpMediaTypeNotSupportedException) e;
            MediaType contentType = mediaTypeNotSupportedException.getContentType();
            message = String.format("不支持[%s]类型", contentType);
            httpCode = HttpCode.ILLEGAL_REQUEST;
            logger.warn("[{}，{}]", requestURI, message);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException methodNotSupportedException = (HttpRequestMethodNotSupportedException) e;
            String method = methodNotSupportedException.getMethod();
            message = String.format("不支持[%s]请求", method);
            httpCode = HttpCode.ILLEGAL_REQUEST;
            logger.warn("[{}，{}]", requestURI, message);
        } else if (e instanceof MultipartException) {
            httpCode = HttpCode.FILE_UPLOAD_EXCEEDS_MAXIMUM_LIMIT;
            logger.warn("[{}，{}]", requestURI, message);
        } else {
            logger.error(e.getMessage(), e);
        }

        if (AppContext.isWebApp || AppContext.webMall || isAjaxRequest(request)) {
            writerJson(response, Rest.newBuilder()
                    .code(httpCode)
                    .message(message)
                    .build());
        }
        return "500";
    }


    private boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    /**
     * 输出json数据
     *
     * @param response
     * @param rest
     */
    private void writerJson(HttpServletResponse response, Rest rest) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(rest);
            out.write(json);
            out.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}

