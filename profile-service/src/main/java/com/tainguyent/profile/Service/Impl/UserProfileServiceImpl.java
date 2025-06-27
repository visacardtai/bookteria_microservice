package com.tainguyent.profile.Service.Impl;

import com.tainguyent.profile.Service.UserProfileService;
import com.tainguyent.profile.dto.request.ProfileCreationRequest;
import com.tainguyent.profile.dto.response.UserProfileResponse;
import com.tainguyent.profile.entity.UserProfile;
import com.tainguyent.profile.mapper.UserProfileMapper;
import com.tainguyent.profile.repository.UserProfileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {
    UserProfileRepository userProfileRepository;
    UserProfileMapper userProfileMapper;

    @Override
    public UserProfileResponse createProfile(ProfileCreationRequest request) {
        UserProfile userProfile = userProfileMapper.toUserProfile(request);
        userProfile = userProfileRepository.save(userProfile);

        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    @Override
    public UserProfileResponse getProfile(String id) {
        UserProfile userProfile = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    @Override
    public List<UserProfileResponse> getAllProfile() {
        return userProfileRepository.findAll().stream().map(userProfileMapper::toUserProfileResponse).collect(Collectors.toList());
    }

    @Override
    public void deleteProfile(String id) {
        userProfileRepository.deleteById(id);
    }
}
