package com.projects.app.bankApplication.DataAccess;

import com.projects.app.bankApplication.Model.User;
import com.projects.app.bankApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserQueryService {

    @Autowired
    private UserRepository userRepository;

    public String getEmailByUserID(Integer UserID) {
        try {
            return userRepository.findByUserID(UserID)
                    .map(User::getEmail)
                    .orElseThrow(() -> new RuntimeException("No email found for user ID: " + UserID));
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            throw new RuntimeException("Failed to retrieve email for user ID: " + UserID, e);
        }
    }

    public String getFullnameByUserID(Integer UserID) {
        try {
            return userRepository.findByUserID(UserID)
                    .map(User::getFullname)
                    .orElseThrow(() -> new RuntimeException("No full name found for user ID: " + UserID));
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve full name for user ID: " + UserID, e);
        }
    }

    public String getSurnameByUserID(Integer UserID) {
        try {
            return userRepository.findByUserID(UserID)
                    .map(User::getSurname)
                    .orElseThrow(() -> new RuntimeException("No surname found for user ID: " + UserID));
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve surname for user ID: " + UserID, e);
        }
    }

    public String getGenderByUserID(Integer UserID) {
        try {
            return userRepository.findByUserID(UserID)
                    .map(User::getGender)
                    .orElseThrow(() -> new RuntimeException("No gender found for user ID: " + UserID));
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve gender for user ID: " + UserID, e);
        }
    }

    public Date getBirthDateByUserID(Integer UserID) {
        try {
            return userRepository.findByUserID(UserID)
                    .map(User::getUserBirthDate)
                    .orElseThrow(() -> new RuntimeException("No birth date found for user ID: " + UserID));
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve birth date for user ID: " + UserID, e);
        }
    }

    public String getPhoneByUserID(Integer UserID) {
        try {
            return userRepository.findByUserID(UserID)
                    .map(User::getPhone)
                    .orElseThrow(() -> new RuntimeException("No phone number found for user ID: " + UserID));
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve phone number for user ID: " + UserID, e);
        }
    }

    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve all users", e);
        }
    }
}
