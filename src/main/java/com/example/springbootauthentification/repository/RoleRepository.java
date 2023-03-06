package com.example.springbootauthentification.repository;
import java.util.Optional;

import com.example.springbootauthentification.models.ERole;
import com.example.springbootauthentification.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(ERole name);

}
