package com.tainguyen.projectredis.repository.httpclient;

import com.tainguyen.projectredis.configuration.AuthenticationRequestInterceptor;
import com.tainguyen.projectredis.dto.request.ProfileCreationRequest;
import com.tainguyen.projectredis.dto.responses.UserProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "profile-service", url = "${app.services.profile}", configuration = {AuthenticationRequestInterceptor.class})
public interface ProfileClient {
    @PostMapping(value = "/internal/users", produces = MediaType.APPLICATION_JSON_VALUE)
    UserProfileResponse createProfile(@RequestBody ProfileCreationRequest request);

}
