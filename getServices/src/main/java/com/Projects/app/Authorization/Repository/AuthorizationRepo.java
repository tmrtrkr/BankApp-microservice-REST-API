package com.Projects.app.Authorization.Repository;

import com.Projects.app.Authorization.Model.AuthorizationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorizationRepo extends JpaRepository<AuthorizationModel, Long> {
    Optional<AuthorizationModel> findByAuthToken(String authToken);
}
