package com.Projects.app.Authorization.Controller;

import com.Projects.app.Authorization.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    // Repository pattern uygulanıcak   DataAccess.O-iş extends CRUD uygulanacak, 

    @GetMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestHeader("Token") String token, @RequestHeader("IP") String IP) {
        try {
            Optional<String> optionalResult = authenticationService.authenticateUser(token, IP);

            return optionalResult
                    .map(user -> ResponseEntity.ok().body(user))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed"));
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error during authentication", e);
        }
    }
}






//TO-DO Servisler Exception Handling, DatabAVase NOT NULL eklenmesi, Dökümantasyon
