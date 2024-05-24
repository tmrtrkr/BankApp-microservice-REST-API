package com.projects.app.bankApplication.Service;

import com.projects.app.bankApplication.Model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Collections;
import java.util.Comparator;

@Service
public class TransactionListService {

    @Autowired
    private com.projects.app.bankApplication.DataAccess.TransactionQueryService transactionQueryService;

    // Get Transactions By AccID with exception handling
    public List<Transaction> getTransactionsByAccId(ArrayList<UUID> debitAccIDs, ArrayList<UUID> depositAccIDs) {
        try {
            // Creating merged AccID list
            ArrayList<UUID> AccIDArray = new ArrayList<>();
            AccIDArray.addAll(debitAccIDs);
            AccIDArray.addAll(depositAccIDs);

            // Creating Transaction List
            List<Transaction> transactionList = new ArrayList<>();

            // Adding each transaction to transactions list
            for (UUID accID : AccIDArray) {

                List<Transaction> transactionLoad = transactionQueryService.getTransactionsByAccID(accID);
                transactionList.addAll(transactionLoad);
            }

            // Sort Transactions by closest datetime
            sortTransactionsByDate(transactionList);

            // Return Transactions as list
            return transactionList;
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            throw new RuntimeException("Failed to retrieve and sort transactions", e);
        }
    }







    // Sorting Transactions by closest date to current time
    private void sortTransactionsByDate(List<Transaction> transactionList) {
        try {
            Collections.sort(transactionList, new Comparator<Transaction>() {
                @Override
                public int compare(Transaction t1, Transaction t2) {
                    return t2.getTransactionDate().compareTo(t1.getTransactionDate());
                }
            });
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            throw new RuntimeException("Failed to sort transactions by date", e);
        }
    }
}
