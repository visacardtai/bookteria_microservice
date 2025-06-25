package com.tainguyen.projectredis.mapper;

import com.tainguyen.projectredis.dto.request.RoleRequest;
import com.tainguyen.projectredis.dto.responses.RoleResponse;
import com.tainguyen.projectredis.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions",ignore = true)
    Role toRole(RoleRequest roleRequest);

    RoleResponse toRoleResponse(Role role);
}
