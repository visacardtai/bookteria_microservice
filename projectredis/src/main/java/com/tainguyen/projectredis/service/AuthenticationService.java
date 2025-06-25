package com.tainguyen.projectredis.service;

import com.nimbusds.jose.JOSEException;
import com.tainguyen.projectredis.dto.AuthenticationDto;
import com.tainguyen.projectredis.dto.IntrospectDto;
import com.tainguyen.projectredis.dto.request.IntrospectRequest;
import com.tainguyen.projectredis.dto.request.LogoutRequest;
import com.tainguyen.projectredis.responses.AuthenticationResponse;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authentication(AuthenticationDto authenticationDto);
    IntrospectDto introspect(IntrospectRequest request) throws JOSEException, ParseException;
    void logout(LogoutRequest request) throws ParseException, JOSEException;
}
