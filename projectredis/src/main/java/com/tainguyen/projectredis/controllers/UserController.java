package com.tainguyen.projectredis.controllers;

import com.tainguyen.projectredis.apiresponse.ApiResponse;
import com.tainguyen.projectredis.dto.UserDto;
import com.tainguyen.projectredis.dto.responses.UserResponse;
import com.tainguyen.projectredis.entity.User;
import com.tainguyen.projectredis.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
public class UserController {

    UserService userService;

    @PostMapping
    private ApiResponse<?> createUser(@Valid @RequestBody UserDto userDto) {
        User user = userService.createUser(userDto);
        return ApiResponse.builder().result(user).build();
    }

    @GetMapping("/{id}")
    private ApiResponse<?> getUserById(@PathVariable("id") UUID id) {
        return ApiResponse.builder().result(userService.getUserById(id)).build();
    }

    @GetMapping
    private ApiResponse<?> getAllUsers() {

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority ->
                log.info(grantedAuthority.getAuthority()));

        List<UserResponse> userList = userService.getAllUsers();
        return ApiResponse.builder()
                .result(userList)
                .build();
    }

    @GetMapping("/myInfo")
    private ApiResponse<?> getMyInfo() {
        return ApiResponse.builder()
                .result(userService.getMyInfo())
                .build();
    }
}
