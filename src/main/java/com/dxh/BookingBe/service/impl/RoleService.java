package com.dxh.BookingBe.service.impl;


import com.dxh.BookingBe.dto.request.RoleRequest;
import com.dxh.BookingBe.dto.response.RoleResponse;
import com.dxh.BookingBe.mapper.RoleMapper;
import com.dxh.BookingBe.repo.PermissionRepository;
import com.dxh.BookingBe.repo.RoleRepository;
import com.dxh.BookingBe.service.interfac.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService implements IRoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    @Override
    public RoleResponse create(RoleRequest request){
        var role = roleMapper.toRole(request);

        // xử lý riêng
        var permissions = permissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public List<RoleResponse> getAll(){
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    @Override
    public void delete(Long role){
        roleRepository.deleteById(role);
    }
}