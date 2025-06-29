package com.dxh.BookingBe.service.interfac;

import com.dxh.BookingBe.dto.request.PermissionRequest;
import com.dxh.BookingBe.dto.response.PermissionResponse;

import java.util.List;

public interface IPermissionService {
    PermissionResponse create(PermissionRequest request);

    List<PermissionResponse> getAll();

    void delete(String permission);
}
