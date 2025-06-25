package com.tainguyen.projectredis.service;

import com.tainguyen.projectredis.dto.request.RoleRequest;
import com.tainguyen.projectredis.dto.responses.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse create(RoleRequest request);

    List<RoleResponse> getAll();
}
