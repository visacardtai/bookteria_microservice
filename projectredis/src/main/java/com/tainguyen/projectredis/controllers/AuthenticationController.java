package com.tainguyen.projectredis.controllers;

import com.nimbusds.jose.JOSEException;
import com.tainguyen.projectredis.apiresponse.ApiResponse;
import com.tainguyen.projectredis.dto.AuthenticationDto;
import com.tainguyen.projectredis.dto.IntrospectDto;
import com.tainguyen.projectredis.dto.request.IntrospectRequest;
import com.tainguyen.projectredis.dto.request.LogoutRequest;
import com.tainguyen.projectredis.responses.AuthenticationResponse;
import com.tainguyen.projectredis.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/auth")
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authentication(@Valid @RequestBody AuthenticationDto authenticationDto) {
        AuthenticationResponse result = authenticationService.authentication(authenticationDto);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result).
                build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectDto> introspect(@Valid @RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectDto>builder()
                .result(result).
                build();
    }

    @PostMapping("/logout")
    ApiResponse<?> logout(@Valid @RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
                .build();
    }
}
