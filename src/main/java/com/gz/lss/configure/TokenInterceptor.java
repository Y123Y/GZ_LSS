package com.gz.lss.configure;

import com.auth0.jwt.interfaces.Claim;
import com.gz.lss.common.LssConstants;
import com.gz.lss.common.ResultGenerator;
import com.gz.lss.common.ResultMsg;
import com.gz.lss.util.JedisClusterUtils;
import com.gz.lss.util.security.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName TokenInterceptor
 * @Author Y
 * @Date 2019/5/21 19:08
 * @Description TODO
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = null;
        ResultMsg result = ResultGenerator.genUnAuthorityResultMsg();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (LssConstants.COOKIE_TOKEN_KEY.equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        if (token != null) {
            log.info(token);
            try {
                Map<String, Claim> claimMap = TokenUtil.verifyToken(token);
                String token_key = claimMap.get(LssConstants.TOKEN_PAYLOAD_KEY).asString();
                if (token.equals(TokenUtil.getToken(token_key))) {
                    request.setAttribute(LssConstants.TOKEN_PAYLOAD_KEY, token_key);
                    request.setAttribute("worker_id", claimMap.get("worker_id").asString());
                    request.setAttribute("authority", claimMap.get("authority").asString());
                    //token未改变
                    TokenUtil.updateToken(token_key, token);
                    //token改变
//                    String updateToken = TokenUtil.updateToken(token_key);
                    //更新token时间
                    TokenUtil.addCookie(response, token);
                    return true;
                }else {
                    result = ResultGenerator.genUnAuthorityResultMsg();
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = ResultGenerator.genFailResultMsg("token认证失败");
            }
        }
        response.getWriter().write(result.toString());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
