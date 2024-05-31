package com.roc.admin.backend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

@Slf4j
public class JWTUtils {
    private final static String secret = "fj32Jfv02Mq33g0f8ioDkw";

    public static String getToken(String userId) {
        try {

            return JWT.create()
                    .withIssuer("tomin")
                    .withClaim("useId", userId)
                    .withIssuedAt(new DateTime().toDate())
                    .withExpiresAt(new DateTime().plusSeconds(30).toDate())
                    .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Generate token failed: ", exception);
        }
    }

    public static String verifyToken(String token) {

        DecodedJWT decodedJWT;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                    // specify any specific claim validations
                    .withIssuer("tomin")
                    // reusable verifier instance
                    .build();

            decodedJWT = verifier.verify(token);
            return decodedJWT.getClaim("useId").asString();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("token verify failed: ", e);
        }

    }
}
