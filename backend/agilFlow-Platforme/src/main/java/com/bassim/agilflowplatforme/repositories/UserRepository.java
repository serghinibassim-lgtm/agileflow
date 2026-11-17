package com.bassim.agilflowplatforme.repositories;

import com.bassim.agilflowplatforme.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String email);


    @Query("SELECT u FROM User u WHERE u.firstname = :userName")
    User findByFirstname(@Param("userName") String userName);
}
