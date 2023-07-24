package com.recados.ApiMuralRecados.repositories;

import com.recados.ApiMuralRecados.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);

    User getReferenceByEmail(String email);
}
