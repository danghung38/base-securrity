package com.dxh.BookingBe.mapper;



import com.dxh.BookingBe.dto.request.RoleRequest;
import com.dxh.BookingBe.dto.response.RoleResponse;

import com.dxh.BookingBe.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    //bỏ qua k map Set<permission> vì list nhận vào là String
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}