package com.pradeep.jwt;

import com.pradeep.constants.JWTConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        String token = getJWTFromRequest(request);
        if(null !=token){

        }
    }
    private String getJWTFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(null != authorizationHeader && authorizationHeader.startsWith(JWTConstants.TOKEN_PREFIX)) {
            return authorizationHeader.substring(7, authorizationHeader.length());
        }
        return null;
    }
}
