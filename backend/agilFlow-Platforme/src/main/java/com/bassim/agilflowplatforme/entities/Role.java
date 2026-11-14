package com.bassim.agilflowplatforme.entities;

import com.bassim.agilflowplatforme.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String role_id;
    @Enumerated(EnumType.STRING)
   private UserRole role;


    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
   private Set<User>users=new HashSet<>();
}
