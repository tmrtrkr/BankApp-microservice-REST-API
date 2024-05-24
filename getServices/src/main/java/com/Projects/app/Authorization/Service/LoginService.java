package com.Projects.app.Authorization.Service;

import com.Projects.app.Authorization.Model.LoginModel;
import com.Projects.app.Authorization.Repository.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginService {
    @Autowired
    private LoginRepo loginRepository;

    public Optional<String> loginUser(String email, String password, String IP) {
        try {
            Optional<LoginModel> optionalUser = loginRepository.findByEmailAndPassword(email, password);

            if (optionalUser.isPresent()) {
                LoginModel user = optionalUser.get();

                // Generate random Token
                String token = UUID.randomUUID().toString();

                // Update user details
                user.setAuthToken(token);
                user.setTokenCreationDate(LocalDateTime.now());
                user.setAuthStats(true);
                user.setAuthIP(IP);  // Set client IP address
                loginRepository.save(user);

                return Optional.of(token);  // Return the token
            } else {
                return Optional.empty();  // If user is not found, return an empty Optional
            }
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            return Optional.empty();  // Return empty to avoid exposing any sensitive information
        }
    }
}
