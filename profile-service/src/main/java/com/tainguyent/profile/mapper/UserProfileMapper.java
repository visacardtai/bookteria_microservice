package com.tainguyent.profile.mapper;

import com.tainguyent.profile.dto.request.ProfileCreationRequest;
import com.tainguyent.profile.dto.response.UserProfileResponse;
import com.tainguyent.profile.entity.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(ProfileCreationRequest request);

    UserProfileResponse toUserProfileResponse(UserProfile userProfile);
}
