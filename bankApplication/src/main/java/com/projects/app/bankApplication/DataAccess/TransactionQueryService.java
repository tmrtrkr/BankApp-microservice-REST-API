package com.projects.app.bankApplication.DataAccess;

import com.projects.app.bankApplication.Model.Transaction;
import com.projects.app.bankApplication.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//Transaction Queries
@Service
public class TransactionQueryService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Get transactions by AccID with exception handling
    public List<Transaction> getTransactionsByAccID(UUID AccID) {
        try {
            List<Transaction> transactions = transactionRepository.findByAccId(AccID);
            if (transactions == null || transactions.isEmpty()) {

                return new ArrayList<>();
            }
            return transactions;
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            throw new RuntimeException("Failed to retrieve transactions for account ID: " + AccID, e);
        }
    }
}
