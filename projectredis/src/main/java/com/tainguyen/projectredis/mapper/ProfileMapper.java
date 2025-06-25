package com.tainguyen.projectredis.mapper;

import com.tainguyen.projectredis.dto.UserDto;
import com.tainguyen.projectredis.dto.request.ProfileCreationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    @Mappings({
            @Mapping(target = "firstName", source = "firstname"),
            @Mapping(target = "lastName", source = "lastname"),
    })
    ProfileCreationRequest toProfileCreationRequest(UserDto userDto);
}
