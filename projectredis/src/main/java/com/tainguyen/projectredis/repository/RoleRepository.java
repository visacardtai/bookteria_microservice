package com.tainguyen.projectredis.repository;

import com.tainguyen.projectredis.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
