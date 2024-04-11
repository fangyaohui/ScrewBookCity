package com.example.common.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;


import java.util.Calendar;
import java.util.Map;

/**
 * @author fangyaohui
 * @version 0.0.3
 * @description JWTUtils
 * @since 2024/3/18 0:13
 */
@Slf4j
public class JWTUtils {

    private static final String SECRET = "!DAR$";

    public static String getToken(Map<String,String>payload){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,30);

        JWTCreator.Builder builder = JWT.create();

        payload.forEach((k,v) -> builder.withClaim(k,v));

        String token = builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(SECRET));

        return token;
    }

    public static DecodedJWT decode(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();

        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        return decodedJWT;
    }

    public static boolean verifyToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();

            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (JWTVerificationException e){
            log.info(e.toString());
            return false;
        }
    }
}