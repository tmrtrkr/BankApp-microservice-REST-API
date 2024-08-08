package com.Projects.app.Authorization.Service;

import com.Projects.app.Authorization.Model.AuthorizationModel;
import com.Projects.app.Authorization.Repository.AuthorizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class AuthorizationService {
    @Autowired
    private AuthorizationRepo authorizationRepo;

    public Optional<String> authorizeUser(String token, String IP) {
        try {
            // Fetching authentication data using the token
            Optional<AuthorizationModel> authModelOptional = authorizationRepo.findByAuthToken(token);

            // Check if token data is present
            if (authModelOptional.isPresent()) {
                AuthorizationModel authorizationModel = authModelOptional.get();

                // Check if the token is expired
                if (ChronoUnit.MINUTES.between(authorizationModel.getTokenCreationDate(), LocalDateTime.now()) > 20) {
                    return Optional.of("This token has expired");
                }

                // Check if user's IP matches and the token is still active
                if (authorizationModel.getAuthIP().equals(IP) && authorizationModel.isAuthStats()) {
                    return Optional.of(authorizationModel.getUserID().toString());
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
