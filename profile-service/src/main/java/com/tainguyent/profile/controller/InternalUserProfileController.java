package com.tainguyent.profile.controller;

import com.tainguyent.profile.Service.UserProfileService;
import com.tainguyent.profile.dto.request.ProfileCreationRequest;
import com.tainguyent.profile.dto.response.UserProfileResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping
public class InternalUserProfileController {
    UserProfileService userProfileService;

    @PostMapping("/internal/users")
    UserProfileResponse createProfile(@Valid @RequestBody ProfileCreationRequest request) {
        return userProfileService.createProfile(request);
    }
}
