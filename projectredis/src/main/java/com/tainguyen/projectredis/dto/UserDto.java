package com.tainguyen.projectredis.dto;

import com.tainguyen.projectredis.dto.responses.RoleResponse;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    @Size(min = 8, message = "USERNAME_INVALID")
    String username;
    String password;
    String firstname;
    String lastname;
    String address;
    LocalDate dob;
    Set<String> roles;

}
