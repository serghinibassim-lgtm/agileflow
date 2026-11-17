package com.bassim.agilflowplatforme.repositories;

import com.bassim.agilflowplatforme.entities.Role;
import com.bassim.agilflowplatforme.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {
    Role findByRole(RoleName role);
}
