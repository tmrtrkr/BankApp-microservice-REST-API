package com.projects.app.bankApplication.Controller;

import com.projects.app.bankApplication.Model.User;
import com.projects.app.bankApplication.Repository.UserRepository;
import com.projects.app.bankApplication.Service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class SignUpController {

    @Autowired
    private SignUpService signUpService;


    @GetMapping(value = "/")
    public String getPage(){
        return "Welcome";
    }

    // Sign-up router with exception handling
    @PostMapping(value = "/signup")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        try {
            String result = signUpService.saveUser(user);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during user registration: " + e.getMessage(), e);
        }
    }
}
