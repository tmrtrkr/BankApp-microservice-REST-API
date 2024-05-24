package com.Projects.app.Authorization.Repository;

import com.Projects.app.Authorization.Model.AuthenticationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthenticationRepo extends JpaRepository<AuthenticationModel, Long> {
    Optional<AuthenticationModel> findByAuthToken(String authToken);
}
