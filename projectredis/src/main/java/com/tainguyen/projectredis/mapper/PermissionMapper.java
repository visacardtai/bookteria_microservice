package com.tainguyen.projectredis.mapper;

import com.tainguyen.projectredis.dto.UserDto;
import com.tainguyen.projectredis.dto.request.PermissionRequest;
import com.tainguyen.projectredis.dto.responses.PermissionResponse;
import com.tainguyen.projectredis.entity.Permission;
import com.tainguyen.projectredis.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest permissionRequest);

    PermissionResponse toPermissionResponse(Permission permission);
}
