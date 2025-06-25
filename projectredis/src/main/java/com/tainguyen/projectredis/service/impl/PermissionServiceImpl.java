package com.tainguyen.projectredis.service.impl;

import com.tainguyen.projectredis.dto.request.PermissionRequest;
import com.tainguyen.projectredis.dto.responses.PermissionResponse;
import com.tainguyen.projectredis.entity.Permission;
import com.tainguyen.projectredis.mapper.PermissionMapper;
import com.tainguyen.projectredis.repository.PermissionRepository;
import com.tainguyen.projectredis.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class PermissionServiceImpl implements PermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    @Override
    public List<PermissionResponse> getAll() {
        var listPermission = permissionRepository.findAll();
        return listPermission.stream().map(permissionMapper::toPermissionResponse).toList();
    }
}
