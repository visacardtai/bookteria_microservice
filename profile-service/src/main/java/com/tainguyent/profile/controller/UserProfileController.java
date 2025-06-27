package com.tainguyent.profile.controller;

import com.tainguyent.profile.Service.UserProfileService;
import com.tainguyent.profile.dto.response.UserProfileResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
public class UserProfileController {
    UserProfileService userProfileService;

    @GetMapping("/{id}")
    UserProfileResponse getProfile(@Valid @PathVariable("id") String id) {
        return userProfileService.getProfile(id);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProfile(@Valid @PathVariable("id") String id) {
        userProfileService.deleteProfile(id);
        return ResponseEntity.status(HttpStatus.OK).body("haha");
    }

    @GetMapping
    List<UserProfileResponse> getAllProfile() {
        return userProfileService.getAllProfile();
    }
}
