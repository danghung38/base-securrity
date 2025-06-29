package com.dxh.BookingBe.configuration;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
//phân quyền trên method
@EnableMethodSecurity
public class SecurityConfig {


//    private final String[] PUBLIC_ENDPOINTS = {"/users",
//            "/auth/token", "/auth/introspect", "/auth/logout", "/auth/refresh"
//    };

    private final String[] PUBLIC_POST_ENDPOINTS = {
            "/auth/token", "/auth/introspect", "/auth/logout", "/auth/refresh", "/users"
    };

    private final String[] PUBLIC_GET_ENDPOINTS = {
            "/users/list",
            "/users/list-with-sort-by-multiple-columns",
            "/users/advance-search-with-specification"
    };

    private final String[] API_DOC_ENDPOINTS = {
            "/api/swagger-ui.html",
            "/api/swagger-ui/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/api/v3/api-docs/**",
            "/api/swagger-resources/**",
            "/api/webjars/**",
            "/users/confirm-email"

    };



    @Autowired
    private CustomJwtDecoder customJwtDecoder;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers(HttpMethod.POST, PUBLIC_POST_ENDPOINTS).permitAll()
                                .requestMatchers(HttpMethod.GET, PUBLIC_GET_ENDPOINTS).permitAll()
                                .requestMatchers(API_DOC_ENDPOINTS).permitAll()
                                //phân quyền trên config/hoặc trên method
//                        .requestMatchers(HttpMethod.GET,"/users").hasRole(Role.ADMIN.name())
//                        .hasAuthority("ROLE_ADMIN") //hasRole và hasAu có thể thay cho nhau
                                .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwtConfigurer ->
                                        //cách cũ chưa clean dùng CustomJwtDecoder
                                        //jwtConfigurer.decoder(jwtDecoder())
                                        jwtConfigurer.decoder(customJwtDecoder)
                                                //dùng ROLE_ thay SCOPE_
                                                .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                                //điểm lỗi xác thực để trả ra lỗi 401
                                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                );

        return httpSecurity.build();
    }


    //chuyển đổi từ SCOPE_ADMIN thành ROLE_ADMIN
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        //đã có ở auth service nên bỏ
//        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }


    //cách cũ chưa clean dùng CustomJwtDecoder
//    @Bean
//    JwtDecoder jwtDecoder(){
//        // signerKey ở application.yaml và thuật toán mã hóa
//        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
//        return NimbusJwtDecoder
//                .withSecretKey(secretKeySpec)
//                .macAlgorithm(MacAlgorithm.HS512)
//                .build();
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
