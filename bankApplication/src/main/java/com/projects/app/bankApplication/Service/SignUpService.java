package com.projects.app.bankApplication.Service;

import com.projects.app.bankApplication.Model.User;
import com.projects.app.bankApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    @Autowired
    private UserRepository userRepository;

    // Sign-Up with exception handling
    public String saveUser(User user) {
        try {
            // Checking if the email already exists in the database
            if (isEmailExists(user.getEmail())) {
                return "Email already exists";
            }

            String userPassword = user.getUserPassword();

            // Checking password constraints
            if (isPassGreaterThanFive(userPassword) && isAlphaNumeric(userPassword)) {
                userRepository.save(user);
                System.out.println("Service" + user.getUserBirthDate());
                return "User registered successfully";
            } else if (!isPassGreaterThanFive(userPassword) && isAlphaNumeric(userPassword)) {
                return "Password must be at least 6 characters long";
            } else if (isPassGreaterThanFive(userPassword) && !isAlphaNumeric(userPassword)) {
                return "Password must consist only of alphanumeric characters.";
            } else {
                return "Password must be at least 6 characters long and consist only of alphanumeric characters.";
            }
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            throw new RuntimeException("Failed to register user due to an error: " + e.getMessage(), e);
        }
    }

    // Checking if password chars are alphanumeric
    public boolean isAlphaNumeric(String pass) {
        return pass.matches("^[a-zA-Z0-9]+$");
    }

    // Checking If password is greater than 5
    public boolean isPassGreaterThanFive(String pass) {
        return pass.length() > 5;
    }

    // Checking if the email already exists
    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
