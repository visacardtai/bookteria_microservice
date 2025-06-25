package com.tainguyen.projectredis.service;

import com.tainguyen.projectredis.dto.UserDto;
import com.tainguyen.projectredis.dto.responses.UserResponse;
import com.tainguyen.projectredis.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserResponse> getAllUsers();
    User createUser(UserDto userDto);
    User getUserById(UUID id);
    UserResponse getMyInfo();
    boolean deleteUserById(String id);
}
