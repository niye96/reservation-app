package com.ices.reservation.manager.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.ices.reservation.common.utils.ReturnUtil;
import com.ices.reservation.manager.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @Author: ny
 * @Date: Created in 14:39 2018/5/8 0008
 */
public class BaseInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String method = request.getMethod();
        String headers = request.getHeader("access-control-request-headers");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        if("OPTIONS".equals(method)) {
            if (headers.indexOf("userkey") >= 0 && headers.indexOf("usertoken") >= 0){
                return true;
            }else{
                response.getWriter().print(JSONObject.toJSON(ReturnUtil.error("无权限请求")));
                return false;
            }
        }

        String key = request.getHeader("userkey");
        String token = request.getHeader("usertoken");
        if(StringUtils.isEmpty(token) || StringUtils.isEmpty(key)) {
            response.getWriter().print(JSONObject.toJSON(ReturnUtil.error("无权限请求")));
            return false;
        }
        String result = redisService.getString(key);
        if(!token.equals(result)) {
            response.getWriter().print(JSONObject.toJSON(ReturnUtil.error("请重新登录")));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
