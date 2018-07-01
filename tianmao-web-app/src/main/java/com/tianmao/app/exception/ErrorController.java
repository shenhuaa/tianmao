package com.tianmao.app.exception;

import com.tianmao.service.common.HttpCode;
import com.tianmao.app.util.Rest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 异常类
 *
 * @author roach
 * @date 2017/12/2
 */
@Controller
@RequestMapping
public class ErrorController {

    @RequestMapping(value = "/403", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public String error403() {
        return "403";
    }

    @RequestMapping(value = "/403", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ResponseBody
    public Rest jsonError403() {
        return Rest.newBuilder()
                .code(HttpCode.FORBIDDEN)
                .build();
    }
    @RequestMapping(value = {"/404", "/error"}, produces = MediaType.TEXT_HTML_VALUE)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String error404() {
        return "404";
    }

    @RequestMapping(value = {"/404", "/error"}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public Rest jsonError404() {
        return Rest.newBuilder()
                .code(HttpCode.NOT_FOUND)
                .build();
    }

    @RequestMapping(value = "/500", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String error500() {
        return "500";
    }




    /**
     * 账号未登陆
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/unauthorized")
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Rest unauthorized() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated() || subject.isRemembered()) {
            subject.logout();
        }
        return Rest.newBuilder().code(HttpCode.UNAUTHORIZED).build();
    }

}
