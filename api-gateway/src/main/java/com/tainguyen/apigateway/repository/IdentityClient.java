package com.tainguyen.apigateway.repository;

import com.tainguyen.apigateway.dto.request.IntrospectRequest;
import com.tainguyen.apigateway.dto.response.ApiResponse;
import com.tainguyen.apigateway.dto.response.IntrospectResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface IdentityClient {
    @PostExchange(url = "/auth/introspect", contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<IntrospectResponse>> introspect(@RequestBody IntrospectRequest request);
}
