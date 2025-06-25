package com.tainguyen.projectredis.service;

import com.tainguyen.projectredis.dto.request.PermissionRequest;
import com.tainguyen.projectredis.dto.responses.PermissionResponse;
import com.tainguyen.projectredis.entity.User;

import java.util.List;

public interface PermissionService {
    PermissionResponse create(PermissionRequest request);

    List<PermissionResponse> getAll();
}
