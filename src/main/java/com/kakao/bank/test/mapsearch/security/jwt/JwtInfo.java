package com.kakao.bank.test.mapsearch.security.jwt;


import com.auth0.jwt.algorithms.Algorithm;

public abstract class JwtInfo {

    public static final String HEADER_NAME = "jwt-header";

    public static final String ISSUER = "swlee";

    public static final String TOKEN_KEY = "swlee.github.io";

    public static final long EXPIRES_LIMIT = 3L;

    public static Algorithm getAlgorithm() {
        try {
            return Algorithm.HMAC256(JwtInfo.TOKEN_KEY);
        } catch (IllegalArgumentException e) {
            return Algorithm.none();
        }
    }
}