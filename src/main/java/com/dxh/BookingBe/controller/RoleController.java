package com.dxh.BookingBe.controller;


import com.dxh.BookingBe.dto.request.RoleRequest;
import com.dxh.BookingBe.dto.response.ApiResponse;
import com.dxh.BookingBe.dto.response.RoleResponse;
import com.dxh.BookingBe.service.impl.RoleService;
import com.dxh.BookingBe.service.interfac.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    IRoleService iRoleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .result(iRoleService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder()
                .result(iRoleService.getAll())
                .build();
    }

    @DeleteMapping("/{roleId}")
    ApiResponse<Void> delete(@PathVariable Long roleId){
        iRoleService.delete(roleId);
        return ApiResponse.<Void>builder().build();
    }
}
