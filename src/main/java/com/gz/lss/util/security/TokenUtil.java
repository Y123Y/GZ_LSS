package com.gz.lss.util.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gz.lss.common.LssConstants;
import com.gz.lss.pojo.Tb_worker;
import com.gz.lss.service.WorkerService;
import com.gz.lss.util.JedisClusterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @ClassName TokenUtil
 * @Author Y
 * @Date 2019/5/20 23:10
 * @Description TODO
 */
@Component
public class TokenUtil {
    private static WorkerService workerService;

    @Autowired
    public void setWorkerService(WorkerService workerService) {
        TokenUtil.workerService = workerService;
    }

    /**
     * 更新redis中的token(每次都重新生成token)
     * @param worker_name   用户名
     */
    public static String updateToken(String worker_name) throws Exception {
        Tb_worker worker = workerService.selectWorkerByLoginName(worker_name);
        return updateToken(worker);
    }

    /**
     * 更新redis中的token(每次都重新生成token)
     * @param worker_id   用户ID
     */
    public static String updateToken(Integer worker_id) throws Exception {
        Tb_worker worker = workerService.selectWorkerById(worker_id);
        return updateToken(worker);
    }

    public static String updateToken(Tb_worker worker) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("worker_id", worker.getWorker_id());
        map.put(LssConstants.TOKEN_PAYLOAD_KEY, worker.getLogin_name());
        map.put("authority", worker.getIdentity());
        String token = createToken(map, LssConstants.DEFAULT_TOKEN_TTL);
        updateToken(worker.getLogin_name(), token, null);
        return token;
    }

    /**
     * 更新redis中的token(使用默认过期时间（3天）)
     * @param worker_name   用户名(键)
     * @param token token
     */
    public static void updateToken(String worker_name, String token) {
        updateToken(worker_name, token, null);
    }

    /**
     * 更新redis中的token(使用自定过期时间)
     * @param worker_name   用户名(键)
     * @param token token
     * @param ttl   过期时间
     */
    public static void updateToken(String worker_name, String token, Long ttl){
        worker_name = getKeyByWorkerName(worker_name);
        JedisClusterUtils.saveString(worker_name, token, Math.toIntExact(Optional.ofNullable(ttl).orElse(LssConstants.DEFAULT_TOKEN_TTL)));
    }

    public static String getToken(String worker_name) {
        String key = getKeyByWorkerName(worker_name);
        return JedisClusterUtils.getString(key);
    }

    public static void deleteToken(String worker_name) {
        String key = getKeyByWorkerName(worker_name);
        JedisClusterUtils.delKey(key);
    }

    public static String getKeyByWorkerName(String worker_name) {
        return "Worker_" + worker_name + "_Token";
    }

    /**
     * 生成有时限token(每次生成的都不一样)
     * @param data payload数据
     * @param time 过期时限
     * @return
     * @throws Exception
     */
    public static String createToken(Map<String, Object> data, Long time) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token;
        JWTCreator.Builder builder = JWT.create().withHeader(map);//header
        if (time != null) {
            builder.withExpiresAt(new Date(System.currentTimeMillis() + time * 1000L));
        }
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            builder.withClaim(entry.getKey(), entry.getValue().toString());
        }
        token = builder.sign(Algorithm.HMAC256(LssConstants.SECRET_KEY));
        return token;
    }

    /**
     * 生成token(不设过期时间，每个用户每次生成都一样)
     * @param data payload数据
     * @return
     * @throws Exception
     */
    public static String createToken(Map<String, Object> data) throws Exception{
        return createToken(data, null);
    }

    /**
     * 验证token
     * @param token 前端传来的token
     * @return 用户名
     * @throws Exception
     */
    public static Map<String, Claim> verifyToken(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(LssConstants.SECRET_KEY)).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaims();
    }

    /**
     * 将cookie放入response传到客户端
     * @param response
     * @param token
     */
    public static void addCookie(HttpServletResponse response, String token){
        Cookie cookie = new Cookie(LssConstants.COOKIE_TOKEN_KEY, token);
        //使用的是GMT时间，北京时间偏移8小时
        cookie.setMaxAge(LssConstants.DEFAULT_TOKEN_TTL.intValue() + 8 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
