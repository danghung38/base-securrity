package com.dxh.BookingBe.configuration;

import com.dxh.BookingBe.dto.response.ApiResponse;
import com.dxh.BookingBe.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    //để truyền về lại SecurityConfig/điểm lỗi
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;

        response.setStatus(errorCode.getStatusCode().value());

        //để trả về body là dạng json
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        //dùng để chuyển ApiResponse thành chuỗi json
        ObjectMapper objectMapper = new ObjectMapper();

        //cái trả về là respons
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));

        //commit về cho client
        response.flushBuffer();
    }
}
