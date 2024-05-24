package com.projects.app.bankApplication.DataAccess;

import com.projects.app.bankApplication.Model.Debit;
import com.projects.app.bankApplication.Repository.DebitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;




@Service
public class DebitQueryService {

    @Autowired
    private DebitRepository debitRepository;

    public ArrayList<UUID> getActiveAccountIdsByUserID(Integer userId) {
        try {
            return debitRepository.findAccountIdsByUserId(userId);
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            throw new RuntimeException("Failed to retrieve account IDs for user ID: " + userId, e);
        }
    }

    public ArrayList<Debit> getActiveAccountsByUserId(Integer userId) {
        try {
            return debitRepository.findActiveAccountsByUserId(userId);
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            throw new RuntimeException("Failed to retrieve active accounts for user ID: " + userId, e);
        }
    }
}
