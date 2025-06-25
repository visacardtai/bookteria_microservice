package com.tainguyen.projectredis.mapper;

import com.tainguyen.projectredis.dto.UserDto;
import com.tainguyen.projectredis.dto.responses.UserResponse;
import com.tainguyen.projectredis.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    User toUser(UserDto userDto);
    UserResponse toUserResponse(User user);

//    void updateUser(@MappingTarget User user, UserDto userDto);

    List<UserResponse> toUserResponseList(List<User> users);
}
