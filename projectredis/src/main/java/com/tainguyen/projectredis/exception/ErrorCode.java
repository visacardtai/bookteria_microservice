package com.tainguyen.projectredis.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USER_EXISTED(-1, "User Existed", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_EXISTED(99, "User Not Exist", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(97, "Authentication fail", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),;

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;
}
