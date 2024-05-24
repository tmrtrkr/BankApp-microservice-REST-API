package com.Projects.app.Authorization.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.Projects.app.Authorization.Model.LoginModel;

import java.util.Optional;

public interface LoginRepo extends JpaRepository<LoginModel, Long> {
    Optional<LoginModel> findByEmailAndPassword(String email, String password);
}



