package com.gz.lss.configure;

import com.gz.lss.common.LssConstants;
import com.gz.lss.pojo.Tb_admin;
import com.gz.lss.pojo.Tb_user;
import com.gz.lss.pojo.Tb_worker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AuthorizedInterceptor implements HandlerInterceptor {
    @Value("${spring.profiles.active}")
    private String action;

    private static final String[] IGNORE_URI = {"/login", "/error", "/register"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        if ("dev".equals(action)) {
            System.out.println("URL: " + request.getRequestURI());

            //log.debug("Handler: " + handler.toString());
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        ResponseBody responseBody = handlerMethod.getMethod().getAnnotation(ResponseBody.class);
        RestController restController = handlerMethod.getBeanType().getAnnotation(RestController.class);

        // 默认用户没有登录
        boolean flag = false;            /*  false 表示正式开放使用的状态 ， true 为了便于调试 */
        // 获得请求的ServletPath
        String servletPath = request.getRequestURI();
        // 判断请求是否需要拦截
        for (String s : IGNORE_URI) {
            if (servletPath.contains(s)) {
                flag = true;
                break;
            }
        }
        // 拦截请求
        if (!flag){
            if(servletPath.startsWith("/user") || servletPath.startsWith("/address")) {
                Tb_user user = (Tb_user) request.getSession().getAttribute(LssConstants.USER_SESSION);
                if(user == null){
                    /** 如果用户没有登录，跳转到登录页面 */
                    request.setAttribute("message", "请先登录再访问网站!");
                    response.sendRedirect("/" + LssConstants.USERLOGIN);
                }else{
                    flag = true;
                }
            }else if(servletPath.startsWith("/worker")) {
                flag = true;
            }else if(servletPath.startsWith("/admin")) {
                Tb_admin admin = (Tb_admin) request.getSession().getAttribute(LssConstants.ADMIN_SESSION);
                if(admin == null){
                    /** 如果管理员没有登录，跳转到登录页面 */
                    request.setAttribute("message", "请先登录再访问网站!");
                    response.sendRedirect("/"+LssConstants.ADMINLOGIN);
                }else{
                    flag = true;
                }
            }else {
                Tb_user user = (Tb_user) request.getSession().getAttribute(LssConstants.USER_SESSION);
                Tb_worker worker = (Tb_worker) request.getSession().getAttribute(LssConstants.WORKER_SESSION);
                Tb_admin admin = (Tb_admin) request.getSession().getAttribute(LssConstants.ADMIN_SESSION);
                if(user == null && worker == null && admin == null){
                    /** 如果用户没有登录，跳转到登录页面 */
                    request.setAttribute("message", "请先登录再访问网站!");
                    response.sendRedirect("/"+LssConstants.USERLOGIN);
                }else{
                    flag = true;
                }
            }
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
