package com.tainguyen.projectredis.configuration;

import com.tainguyen.projectredis.entity.Role;
import com.tainguyen.projectredis.entity.User;
import com.tainguyen.projectredis.repository.RoleRepository;
import com.tainguyen.projectredis.repository.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;

@Configuration
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationConfig {

    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                var roles = roleRepository.findAllById(List.of("ADMIN"));
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles(new HashSet<Role>(roles))
                        .build();
                userRepository.save(user);
                log.warn("Created account admin default success!!!");
            }
        };
    }
}
