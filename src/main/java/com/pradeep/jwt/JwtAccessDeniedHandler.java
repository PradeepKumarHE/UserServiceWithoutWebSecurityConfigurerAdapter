package com.pradeep.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pradeep.response.CustomHttpResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.io.OutputStream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException accessDeniedException) throws IOException {

        httpServletResponse.setContentType(APPLICATION_JSON_VALUE);

        CustomHttpResponse customHttpResponse=new CustomHttpResponse();
        customHttpResponse.setPath(httpServletRequest.getRequestURI());
        customHttpResponse.setHttpStatusCode(HttpStatus.UNAUTHORIZED.value());
        customHttpResponse.setTitle(accessDeniedException.getMessage());
        customHttpResponse.setMessage(accessDeniedException.getCause().getMessage());

        ObjectMapper objectMapper = new ObjectMapper();
        OutputStream outputStream = httpServletResponse.getOutputStream();
        objectMapper.writeValue(outputStream, customHttpResponse);
        outputStream.flush();
    }
}
