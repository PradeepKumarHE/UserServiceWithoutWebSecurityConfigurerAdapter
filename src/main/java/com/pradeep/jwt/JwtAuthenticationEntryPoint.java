package com.pradeep.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pradeep.response.CustomHttpResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,AuthenticationException authException) throws IOException, ServletException {

        log.debug("Jwt AuthenticationEntry Point");

       // https://www.devglan.com/spring-security/exception-handling-in-spring-security
        httpServletResponse.setContentType(APPLICATION_JSON_VALUE);

        CustomHttpResponse customHttpResponse=new CustomHttpResponse();
        customHttpResponse.setPath(httpServletRequest.getRequestURI());

        if (authException instanceof BadCredentialsException) {
            customHttpResponse.setHttpStatusCode(HttpStatus.FORBIDDEN.value());
            customHttpResponse.setTitle(authException.getMessage());
            customHttpResponse.setMessage(authException.getCause().getMessage());
        }else{
            customHttpResponse.setHttpStatusCode(HttpStatus.UNAUTHORIZED.value());
            customHttpResponse.setTitle(authException.getMessage());
            customHttpResponse.setMessage(authException.getCause().getMessage());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        OutputStream outputStream = httpServletResponse.getOutputStream();
        objectMapper.writeValue(outputStream, customHttpResponse);
        outputStream.flush();

    }
}
