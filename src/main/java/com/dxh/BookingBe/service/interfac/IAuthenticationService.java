package com.dxh.BookingBe.service.interfac;

import com.dxh.BookingBe.dto.request.AuthenticationRequest;
import com.dxh.BookingBe.dto.request.IntrospectRequest;
import com.dxh.BookingBe.dto.request.LogoutRequest;
import com.dxh.BookingBe.dto.request.RefreshRequest;
import com.dxh.BookingBe.dto.response.AuthenticationResponse;
import com.dxh.BookingBe.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface IAuthenticationService {
    //    kiểm tra token
    IntrospectResponse introspect(IntrospectRequest request)
                throws JOSEException, ParseException;

    //đăng nhập và tạo token
    AuthenticationResponse authenticate(AuthenticationRequest request);

    //token sắp hết hạn thì gia hạn
    AuthenticationResponse refreshToken(RefreshRequest request)
            throws ParseException, JOSEException;

    void logout(LogoutRequest request) throws ParseException, JOSEException;
}
