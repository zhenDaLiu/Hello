package com.yuntongxun.ytx.fast.config.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yuntongxun.ytx.utils.PropertiesUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Objects;

/**
 * jwt工具类
 * @author luocc
 */
public class JWTUtil {

    private static final long EXPIRE_TIME_PREFIX = Long.parseLong(Objects.requireNonNull(PropertiesUtil.getProperty("token.jwt.expireTime")));
    private static final long EXPIRE_TIME = EXPIRE_TIME_PREFIX * 1000;
    /**
     * 发行人 签发者
     */
    private final static String ISSUER = "YTX_FAST";

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return "";
        }
    }

    /**
     * 获取用户ID
     *
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     *
     * @param username 用户名
     * @param secret   用户的密码
     * @return 加密的token
     */
    public static String sign(String userId, String username, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            if(StringUtils.isEmpty(secret)){
                secret = username;
            }
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带username信息
            return JWT.create()
                    .withClaim("userId", userId)
                    .withClaim("username", username)
                    .withIssuer(ISSUER)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }


    public static String getToken() {
        Subject subject = SecurityUtils.getSubject();
        return subject.getPrincipal().toString();
    }


    public static String getUsername() {
        try {
            DecodedJWT jwt = JWT.decode(getToken());
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return "";
        }
    }


    public static Long getUserId() {
        try {
            DecodedJWT jwt = JWT.decode(getToken());
            return jwt.getClaim("userId").asLong();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static Date getExpiresAt() {
        try {
            DecodedJWT jwt = JWT.decode(getToken());
            return jwt.getExpiresAt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        boolean verify = JWTUtil.verify(
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJsdW9jYyIsImV4cCI6MTUzNzMzMTY1OCwidXNlcklkIjoxLCJ1c2VybmFtZSI6Imx1b2NjIn0.btbEexpknzO0TOlL0b6Q1fcWvZYj3I9UFXkmQ7P6qok",
                "123456"
        );

        System.out.println(verify);
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJsdW9jYyIsImV4cCI6MTUzNzMyNDk4OCwidXNlcklkIjoxLCJ1c2VybmFtZSI6Imx1b2NjIn0.BRiHIaw8z79sn9AUe3DzICHLY8fcHHSjgTChcG5tS0A";
//        DecodedJWT jwt = JWT.decode(token);
//        Date expiresAt = jwt.getExpiresAt();
//        System.out.println(expiresAt);
    }

}