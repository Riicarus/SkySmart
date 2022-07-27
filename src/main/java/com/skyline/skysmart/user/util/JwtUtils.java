package com.skyline.skysmart.user.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Map;

/**
 * [FEATURE INFO]<br/>
 * JWT Util
 *
 * @author Skyline
 * @create 2022/6/10 16:06
 * @since 1.0.0
 */
public class JwtUtils {

    private static final String SECRET = "token!@#$%^7890";
    private static final long EXPIRATION = 12 * 60 * 60 * 1000;
    private static final String ISSUER = "Skyline";


    /**
     * 生成token
     * @param map:要加密的信息
     * @return 令牌
     */
    public static String sign(Map<String, String> map) {
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach(builder::withClaim);
        // issuer
        builder.withIssuer(ISSUER);
        // expireTime
        Date date = new Date(System.currentTimeMillis() + EXPIRATION);
        builder.withExpiresAt(date);
        // 加密
        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 验证token合法性/获取token全部信息
     * @param token token
     * @return verify
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }

    /**
     * 获取token单个信息
     * @param token token
     * @param key key
     * @return value
     */
    public static String getInfo(String token, String key) {
        DecodedJWT verify = verify(token);
        return verify.getClaim(key).asString();
    }

}
