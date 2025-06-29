package com.dxh.BookingBe.service.impl;



import com.dxh.BookingBe.dto.request.PermissionRequest;
import com.dxh.BookingBe.dto.response.PermissionResponse;
import com.dxh.BookingBe.entity.Permission;
import com.dxh.BookingBe.mapper.PermissionMapper;
import com.dxh.BookingBe.repo.PermissionRepository;
import com.dxh.BookingBe.service.interfac.IPermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService implements IPermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse create(PermissionRequest request){
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    @Override
    public List<PermissionResponse> getAll(){
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @Override
    public void delete(String permission){
        permissionRepository.deleteById(permission);
    }
}