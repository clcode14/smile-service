package org.flightythought.smile.appserver.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.Map;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JwtTokenUtil
 * @CreateTime 2019/3/17 21:15
 * @Description: TODO
 */
public class JwtTokenUtil {
    public static final String TOKEN_HEADER = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SECRET = "flightyThoughtJwtSecret";

    /**
     * 签发者
     */
    private static final String ISS = "FlightyThought";

    /**
     * 过期时间 单位秒
     */
    private static final long EXPIRATION = 3600L;

    /**
     * 选择了记住我之后的过期时间为7天
     */
    private static final long EXPIRATION_REMEMBER = 604800L;

    /**
     * 创建token
     * @param username
     * @param isRememberMe
     * @return
     */
    public static String createToken(String username, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
        return token;
    }

    /**
     * 从token中获取用户名
     * @param token
     * @return
     */
    public static String getUsername(String token){
        return getTokenBody(token).getSubject();
    }

    /**
     * 判断是否已过期
     * @param token
     * @return
     */
    public static boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }

    public static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public static void main(String[] args) {
        String token = JwtTokenUtil.createToken("asds", false);
        System.out.println(token);
        String username = JwtTokenUtil.getUsername("eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJGbGlnaHR5VGhvdWdodCIsInN1YiI6ImFzZHMiLCJpYXQiOjE1NTI5MjA1MTIsImV4cCI6MTU1MzUyNTMxMn0.brRhIxRvc0TGiOvuInzMba-GcpsB0QYcjE7We9eTrJhOl5QTJhcta_UVBsecBV292qAf6qZolE8fQ6F060mA_A");
        System.out.println(username);
        Boolean flag = JwtTokenUtil.isExpiration("eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJGbGlnaHR5VGhvdWdodCIsInN1YiI6ImFzZHMiLCJpYXQiOjE1NTI5MjA1MTIsImV4cCI6MTU1MzUyNTMxMn0.brRhIxRvc0TGiOvuInzMba-GcpsB0QYcjE7We9eTrJhOl5QTJhcta_UVBsecBV292qAf6qZolE8fQ6F060mA_A");
        System.out.println(flag);
        System.out.println(JwtTokenUtil.getTokenBody("eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJGbGlnaHR5VGhvdWdodCIsInN1YiI6ImFzZHMiLCJpYXQiOjE1NTI5MjA1MTIsImV4cCI6MTU1MzUyNTMxMn0.brRhIxRvc0TGiOvuInzMba-GcpsB0QYcjE7We9eTrJhOl5QTJhcta_UVBsecBV292qAf6qZolE8fQ6F060mA_A").getExpiration());
        JwtTokenUtil.getTokenBody("eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJGbGlnaHR5VGhvdWdodCIsInN1YiI6ImFzZHMiLCJpYXQiOjE1NTI5MjA1MTIsImV4cCI6MTU1MzUyNTMxMn0.brRhIxRvc0TGiOvuInzMba-GcpsB0QYcjE7We9eTrJhOl5QTJhcta_UVBsecBV292qAf6qZolE8fQ6F060mA_A").setExpiration(new Date());
        System.out.println(JwtTokenUtil.getTokenBody("eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJGbGlnaHR5VGhvdWdodCIsInN1YiI6ImFzZHMiLCJpYXQiOjE1NTI5MjA1MTIsImV4cCI6MTU1MzUyNTMxMn0.brRhIxRvc0TGiOvuInzMba-GcpsB0QYcjE7We9eTrJhOl5QTJhcta_UVBsecBV292qAf6qZolE8fQ6F060mA_A").getExpiration());
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = JwtTokenUtil.isExpiration("eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJGbGlnaHR5VGhvdWdodCIsInN1YiI6ImFzZHMiLCJpYXQiOjE1NTI5MjA1MTIsImV4cCI6MTU1MzUyNTMxMn0.brRhIxRvc0TGiOvuInzMba-GcpsB0QYcjE7We9eTrJhOl5QTJhcta_UVBsecBV292qAf6qZolE8fQ6F060mA_A");
        System.out.println(flag);
    }
}
