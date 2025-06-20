package com.dxh.BookingBe.controller;

import com.dxh.BookingBe.dto.request.UserCreationRequest;
import com.dxh.BookingBe.dto.response.ApiResponse;
import com.dxh.BookingBe.dto.response.UserResponse;
import com.dxh.BookingBe.service.AwsS3Service;
import com.dxh.BookingBe.service.impl.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    UserService userService;
    private final AwsS3Service awsS3Service;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();


        return ApiResponse.<UserResponse>builder()
                .code(HttpStatus.CREATED.value())
                .result(userService.createUser(request))
                .build();
    }

    //test ảnh
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ApiResponse<String> addNewRoom(
            @RequestParam(value = "photo", required = false) MultipartFile photo
    ) {

        return ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .result(awsS3Service.saveImageToS3(photo))
                .build();
    }

    //xác thực email
    @Operation(summary = "Confirm Email", description = "Confirm email for account")
    @GetMapping("/confirm-email")
    public void confirmEmail(@RequestParam String secretCode, HttpServletResponse response) throws IOException {
        log.info("Confirm email for account with secretCode: {}", secretCode);


        try {
            // TODO check or compare secret code from db
        } catch (Exception e) {
            log.error("Verification fail", e.getMessage(), e);
        } finally {
            response.sendRedirect("https://tayjava.vn/wp-admin/");
        }
    }
}
