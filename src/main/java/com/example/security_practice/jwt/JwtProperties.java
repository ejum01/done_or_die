package com.example.security_practice.jwt;

import com.example.security_practice.auth.PrincipalDetails;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : ejum
 * @fileName : JwtProperties
 * @since : 8/20/24
 */
public class JwtProperties {
    public static String SECRET = "doneOrDie";
    public static int EXPIRATION_TIME = 1000*60*60*2;
    public static String TOKEN_PREFIX = "Bearer ";
    public static String ACC_HEADER_STRING = "Authorization";
    public static String REF_HEADER_STRING = "Refresh";
    public static int REF_EXPIRATION_TIME = 1000*60*60*24*14;

}
