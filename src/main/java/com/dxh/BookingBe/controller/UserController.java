package com.dxh.BookingBe.controller;

import com.dxh.BookingBe.dto.request.UserCreationRequest;
import com.dxh.BookingBe.dto.response.ApiResponse;
import com.dxh.BookingBe.dto.response.PageResponse;
import com.dxh.BookingBe.dto.response.UserResponse;
import com.dxh.BookingBe.service.AwsS3Service;
import com.dxh.BookingBe.service.impl.UserService;
import com.dxh.BookingBe.service.interfac.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    IUserService iUserService;
    AwsS3Service awsS3Service;

    @Operation(method = "POST", summary = "Add new user",
            description = "Send a request via this API to create new user")
    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();


        return ApiResponse.<UserResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Successfully created new user")
                .result(iUserService.createUser(request))
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


    @Operation(summary = "Get list of users per pageNo and sort by one column", description = "Send a request via this API to get user list by pageNo and pageSize")
    @GetMapping("/list")
    public ApiResponse<PageResponse<List<UserResponse>>> getAllUsersSortBy(@RequestParam(defaultValue = "1", required = false) Integer pageNo,
                                                                     @Min(value = 1,message = "pageSize must be greater than 1") @RequestParam(defaultValue = "20", required = false) Integer pageSize,
                                                                     @RequestParam(required = false) String sortBy) {
        log.info("get all users");
        return ApiResponse.<PageResponse<List<UserResponse>>>builder()
                .code(HttpStatus.OK.value())
                .message("Successfully get user list")
                .result(iUserService.getAllUsersSortBy(pageNo,pageSize,sortBy))
                .build();
    }

    @Operation(summary = "Get list of users per pageNo and sort by multiple columns", description = "Send a request via this API to get user list by pageNo and pageSize")
    @GetMapping("/list-with-sort-by-multiple-columns")
    public ApiResponse<PageResponse<List<UserResponse>>> getAllUsersSortByMultipleColumns(@RequestParam(defaultValue = "1", required = false) int pageNo,
                                                                           @Min(value = 1,message = "pageSize must be greater than 1") @RequestParam(defaultValue = "20", required = false) int pageSize,
                                                                           @RequestParam(required = false) String... sorts) {
        log.info("get all users");
        return ApiResponse.<PageResponse<List<UserResponse>>>builder()
                .code(HttpStatus.OK.value())
                .message("Successfully get user list")
                .result(iUserService.getAllUsersSortByMultipleColumns(pageNo,pageSize,sorts))
                .build();
    }

    @Operation(summary = "Advance search query by specifications", description = "Return list of users")
    @GetMapping( "/advance-search-with-specification")
    public ApiResponse<PageResponse<List<UserResponse>>> advanceSearchWithSpecifications(Pageable pageable,
                                                           @RequestParam(required = false) String[] user,
                                                           @RequestParam(required = false) String[] role) {
        return ApiResponse.<PageResponse<List<UserResponse>>>builder()
                .code(HttpStatus.OK.value())
                .message("Successfully get user list")
                .result(iUserService.advanceSearchWithSpecifications(pageable,user,role))
                .build();
    }
}
