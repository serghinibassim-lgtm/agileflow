package com.bassim.agilflowplatforme.repositories;

import com.bassim.agilflowplatforme.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
