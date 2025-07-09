package com.tainguyen.projectredis.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tainguyen.projectredis.dto.UserDto;
import com.tainguyen.projectredis.dto.responses.RoleResponse;
import com.tainguyen.projectredis.dto.responses.UserResponse;
import com.tainguyen.projectredis.entity.Role;
import com.tainguyen.projectredis.entity.User;
import com.tainguyen.projectredis.exception.AppException;
import com.tainguyen.projectredis.exception.ErrorCode;
import com.tainguyen.projectredis.mapper.ProfileMapper;
import com.tainguyen.projectredis.mapper.UserMapper;
import com.tainguyen.projectredis.repository.RoleRepository;
import com.tainguyen.projectredis.repository.UserRepository;
import com.tainguyen.projectredis.repository.httpclient.ProfileClient;
import com.tainguyen.projectredis.service.BaseRedisService;
import com.tainguyen.projectredis.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    private static final String USER_CACHE_PREFIX = "USER::";
    BaseRedisService redisService;
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    ProfileClient profileClient;
    ProfileMapper profileMapper;

    @Override
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserResponse> getAllUsers() {
        return userMapper.toUserResponseList(userRepository.findAll());
    }

    @Override
    public User createUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        var roles = roleRepository.findAllById(userDto.getRoles());

        user.setRoles(new HashSet<>(roles));

        user = userRepository.save(user);

        var profileRequest = profileMapper.toProfileCreationRequest(userDto);
        profileRequest.setUserId(user.getId().toString());

        var profileResponse = profileClient.createProfile(profileRequest);
        
        return user;
    }

    @Override
    @PostAuthorize("returnObject.username == authentication.name")
    public User getUserById(UUID id) {
        String redisKey = USER_CACHE_PREFIX + id.toString();
        Object cachedUser = redisService.get(redisKey);
        if (cachedUser instanceof User) {
            return (User) cachedUser;
        } else if (cachedUser instanceof LinkedHashMap) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            return mapper.convertValue(cachedUser, User.class);
        }
        User user =  userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_EXISTED));
        redisService.set(redisKey, user, Duration.ofMinutes(5));
        return user;
    }

    @Override
    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();

        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }

    @Override
    public boolean deleteUserById(String id) {
        return false;
    }
}
