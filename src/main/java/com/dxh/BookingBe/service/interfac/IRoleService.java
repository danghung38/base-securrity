package com.dxh.BookingBe.service.interfac;

import com.dxh.BookingBe.dto.request.RoleRequest;
import com.dxh.BookingBe.dto.response.RoleResponse;

import java.util.List;

public interface IRoleService {
    RoleResponse create(RoleRequest request);

    List<RoleResponse> getAll();

    void delete(Long role);
}
