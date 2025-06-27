package com.tainguyent.profile.Service;

import com.tainguyent.profile.dto.request.ProfileCreationRequest;
import com.tainguyent.profile.dto.response.UserProfileResponse;

import java.util.List;

public interface UserProfileService {
    UserProfileResponse createProfile(ProfileCreationRequest request);
    UserProfileResponse getProfile(String id);
    List<UserProfileResponse> getAllProfile();
    void deleteProfile(String id);
}
