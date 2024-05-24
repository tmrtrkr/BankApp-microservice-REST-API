package com.projects.app.bankApplication.Service;

import com.projects.app.bankApplication.Model.Account;
import com.projects.app.bankApplication.Model.Debit;
import com.projects.app.bankApplication.Model.Deposit;
import com.projects.app.bankApplication.Repository.DebitRepository;
import com.projects.app.bankApplication.Repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountsService {

    @Autowired
    private DebitRepository debitRepository;
    @Autowired
    private DepositRepository depositRepository;

    public List<Account> getMergedAccountList(Integer userID) {
        try {
            List<Account> mergedAccountList = new ArrayList<>();

            mergedAccountList.addAll(debitRepository.findActiveAccountsByUserId(userID));
            mergedAccountList.addAll(depositRepository.findActiveAccountsByUserId(userID));

            if (mergedAccountList.isEmpty()) {
                throw new RuntimeException("No active accounts found for user ID: " + userID);
            }

            return mergedAccountList;
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            throw new RuntimeException("Failed to retrieve merged account list for user ID: " + userID, e);
        }
    }

    public Account findAccountByAccIDAndUserID(UUID accID, Integer userID) {
        try {
            List<Debit> debits = debitRepository.findActiveAccountsByUserId(userID);

            for (Debit debit : debits) {
                if (debit.getAccID().equals(accID)) {
                    return debit;
                }
            }

            List<Deposit> deposits = depositRepository.findActiveAccountsByUserId(userID);

            for (Deposit deposit : deposits) {
                if (deposit.getAccID().equals(accID)) {
                    return deposit;
                }
            }

            throw new RuntimeException("Account not found for ID: " + accID + " and User ID: " + userID);
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            throw new RuntimeException("Failed to find account by Account ID and User ID", e);
        }
    }
}
