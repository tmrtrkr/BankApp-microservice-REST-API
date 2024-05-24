package com.projects.app.bankApplication.DataAccess;

import com.projects.app.bankApplication.Model.Deposit;
import com.projects.app.bankApplication.Repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class DepositQueryService {

    @Autowired
    private DepositRepository depositRepository;

    public ArrayList<UUID> getActiveAccountIdsByUserId(Integer userId) {
        try {
            return depositRepository.findActiveAccountIdsByUserId(userId);
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            throw new RuntimeException("Failed to retrieve account IDs for user ID: " + userId, e);
        }
    }

    public ArrayList<Deposit> getActiveAccountsByUserId(Integer userId) {
        try {
            return depositRepository.findActiveAccountsByUserId(userId);
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            throw new RuntimeException("Failed to retrieve active accounts for user ID: " + userId, e);
        }
    }
}
