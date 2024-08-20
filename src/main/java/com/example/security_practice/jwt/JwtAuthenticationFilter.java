package com.example.security_practice.jwt;

import com.example.security_practice.auth.PrincipalDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

/**
 * @author : ejum
 * @fileName : JwtAuthenticationFilter
 * @since : 8/19/24
 */
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        String username = obtainUsername(request);
        String password = obtainPassword(request);
        System.out.println("여기는 jwt class");
        System.out.println(username);
        System.out.println(password);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        return authenticationManager.authenticate(authToken);
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("successfulAuthentication autResult= {}", authResult.getPrincipal().toString());

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
        String accessToken = jwtUtil.createAccessToken(principalDetails);

        log.info("principalDetails =={}",principalDetails);
        log.info("principalDetails =={}",principalDetails.toString());

        response.addHeader(JwtProperties.ACC_HEADER_STRING, JwtProperties.TOKEN_PREFIX + accessToken);

        // Ensure that super method is called after processing JWT
        super.successfulAuthentication(request, response, chain, authResult);
    }


    //    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, Authentication authResult) throws ServletException, IOException {
//        log.info("successfulAuthentication autResult= {}",authResult);
//
//        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
//
//        String accessToken = jwtUtil.createAccessToken(principalDetails);
//        System.out.println("success");
//
//        response.addHeader(JwtProperties.ACC_HEADER_STRING,JwtProperties.TOKEN_PREFIX+accessToken);
//        log.info(response.getHeader("aa"));
//        super.successfulAuthentication(request, response, filterChain, authResult);
//
//    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failAuthenticationException){
        System.out.println("unsuccess");
    }
}
