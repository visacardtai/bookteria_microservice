package com.tainguyen.apigateway.service;

import com.tainguyen.apigateway.dto.response.ApiResponse;
import com.tainguyen.apigateway.dto.response.IntrospectResponse;
import reactor.core.publisher.Mono;

public interface IdentityService {
    Mono<ApiResponse<IntrospectResponse>> introspect(String token);
}
