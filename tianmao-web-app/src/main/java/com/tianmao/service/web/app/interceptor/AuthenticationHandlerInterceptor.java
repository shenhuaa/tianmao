package com.tianmao.service.web.app.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.tianmao.service.common.HttpCode;
import com.tianmao.service.web.app.config.AppContext;
import com.tianmao.service.web.app.util.Rest;
import com.tianmao.service.web.app.constant.IConstants;
import com.tianmao.service.web.app.token.UserTokenManager;
import com.tianmao.service.web.app.util.APIParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证拦截器
 *
 * @author roach
 * @date 2017/11/14
 */
public class AuthenticationHandlerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationHandlerInterceptor.class);

    @Autowired
    private UserTokenManager userTokenManager;

    /**
     * 在请求处理之前进行调用
     *
     * @param request
     * @param response
     * @param object
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String debug = request.getParameter("debug");
        String sign = request.getParameter("sign");
        String timestamp = request.getParameter("timestamp");
        String token = request.getParameter("token");

        Rest.Builder rest = Rest.newBuilder();
        String active = AppContext.active;
        if ("dev".equals(active) && "true".equals(debug)) {
            logger.warn("开发环境，调试放行");
            return true;
        }
        if (StringUtils.isEmpty(timestamp)) {
            logger.warn("timestamp不能为空");
            APIParamUtil.ToJSONString(rest.code(HttpCode.MISSING_PARAMETERS).message("timestamp不能为空").build(), response);
            return false;
        }
        if (StringUtils.isEmpty(sign)) {
            logger.warn("sign不能为空");
            APIParamUtil.ToJSONString(rest.code(HttpCode.MISSING_PARAMETERS).message("sign不能为空").build(), response);
            return false;
        }

        long time = Long.parseLong(timestamp);
        //验证时间是否过期
        if ((time > (System.currentTimeMillis() + IConstants.CODE_TIMEOUT) || time < (System.currentTimeMillis() - IConstants.CODE_TIMEOUT))) {
            APIParamUtil.ToJSONString(rest.code(HttpCode.ADDRESS_EXPIRED).message("时间过期了").build(), response);
            logger.warn("时间过期了");
            return false;
        }
        JSONObject json = APIParamUtil.getParam(request);
        if (!APIParamUtil.sign(json)) {
            APIParamUtil.ToJSONString(rest.code(HttpCode.SIGN_ERROR).build(), response);
            logger.warn("签名不对");
            return false;
        }
        String userId = request.getParameter("userId");
        if (StringUtils.isEmpty(userId)) {
            APIParamUtil.ToJSONString(rest.code(HttpCode.MISSING_PARAMETERS).message("userId不能为空").build(), response);
            logger.warn("userId不能为空[{}]",userId);
            return false;
        }
        if (StringUtils.isEmpty(token)) {
            APIParamUtil.ToJSONString(rest.code(HttpCode.TOKEN_EXPIRED).message("token不能为空").build(), response);
            logger.warn("token不能为空");
            return false;
        }
        String userToken = userTokenManager.getToken(Long.valueOf(userId));
        if (StringUtils.isEmpty(userToken) || !userToken.equals(token)) {
            APIParamUtil.ToJSONString(rest.code(HttpCode.TOKEN_EXPIRED).message("token过期").build(), response);
            logger.warn("用户token不一致");
            return false;
        }
        return true;
    }

    /**
     * 请求处理之后进行调用
     *
     * @param request
     * @param response
     * @param object
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用
     *
     * @param request
     * @param response
     * @param object
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception e) throws Exception {

    }

}


