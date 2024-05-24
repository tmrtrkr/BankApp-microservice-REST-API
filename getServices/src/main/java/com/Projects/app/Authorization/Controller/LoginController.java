package com.Projects.app.Authorization.Controller;

import com.Projects.app.Authorization.Model.LoginModel;
import com.Projects.app.Authorization.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

@RestController
public class
LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginModel loginModel, HttpServletRequest request) {
        try {
            String clientIp = request.getRemoteAddr();
            Optional<String> optionalToken = loginService.loginUser(loginModel.getEmail(), loginModel.getPassword(), clientIp);


            return optionalToken
                    .map(token -> ResponseEntity.ok(token))  // If token is present, return it
                    .orElseGet(() -> ResponseEntity.badRequest().body("Wrong password or email"));  // If not, return error message
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error during login", e);
        }
    }
}
