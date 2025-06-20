package com.dxh.BookingBe.mapper;



import com.dxh.BookingBe.dto.request.PermissionRequest;
import com.dxh.BookingBe.dto.response.PermissionResponse;
import com.dxh.BookingBe.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
