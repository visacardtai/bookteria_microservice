package com.tainguyent.profile.Service;

import com.tainguyent.profile.dto.request.ProfileCreationRequest;
import com.tainguyent.profile.dto.response.UserProfileResponse;

public interface UserProfileService {
    UserProfileResponse createProfile(ProfileCreationRequest request);
    UserProfileResponse getProfile(String id);
    void deleteProfile(String id);
}
