package com.Projects.app.Authorization.Service;

import com.Projects.app.Authorization.Model.AuthenticationModel;
import com.Projects.app.Authorization.Repository.AuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationRepo authenticationRepo;

    public Optional<String> authenticateUser(String token, String IP) {
        try {
            // Fetching authentication data using the token
            Optional<AuthenticationModel> authModelOptional = authenticationRepo.findByAuthToken(token);

            // Check if token data is present
            if (authModelOptional.isPresent()) {
                AuthenticationModel authenticationModel = authModelOptional.get();

                // Check if the token is expired
                if (ChronoUnit.MINUTES.between(authenticationModel.getTokenCreationDate(), LocalDateTime.now()) > 20) {
                    return Optional.of("This token has expired");
                }

                // Check if user's IP matches and the token is still active
                if (authenticationModel.getAuthIP().equals(IP) && authenticationModel.isAuthStats()) {
                    return Optional.of(authenticationModel.getUserID().toString());
                } else {
                    return Optional.of("Authentication failed: IP mismatch or inactive token");
                }
            } else {
                return Optional.of("Something went wrong during authentication");
            }
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            return Optional.of("Authentication failed: An error occurred during authentication");
        }
    }
}
