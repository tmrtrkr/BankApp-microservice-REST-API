package com.projects.app.bankApplication.Controller;

import com.projects.app.bankApplication.Model.Transaction;
import com.projects.app.bankApplication.Service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projects.app.bankApplication.DataAccess.DebitQueryService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class TransactionController {

    @Autowired
    private DebitQueryService DebitQueryService;

    @Autowired
    private com.projects.app.bankApplication.DataAccess.DepositQueryService DepositQueryService;

    @Autowired
    private TransactionListService TransactionListService;

    @Autowired
    private com.projects.app.bankApplication.DataAccess.HttpRequestService HttpRequestService;


    @GetMapping("/alltransactions")
    public ResponseEntity<?> getUserTransactions(@RequestHeader String Token, HttpServletRequest httpServletRequest) {

        // Get UserID or error message from Authentication response
        String response = HttpRequestService.httpGetUserIDByToken(Token, httpServletRequest.getRemoteAddr());
        Integer resolvedUserId;

        try {
            // Try to parse the response to integer assuming it's a valid UserID
            resolvedUserId = Integer.parseInt(response);

            List<Transaction> transactions = TransactionListService.getTransactionsByAccId(
                    DepositQueryService.getActiveAccountIdsByUserId(resolvedUserId),
                    DebitQueryService.getActiveAccountIdsByUserID(resolvedUserId));

            return ResponseEntity.ok(transactions);  // Return the transactions list with status 200 OK
        } catch (NumberFormatException e) {
            // If parsing fails, it means the response was an error message
            return ResponseEntity.badRequest().body(response);  // Return the error message with status 400 Bad Request
        }
    }


    @GetMapping("/accountTransaction")
    public ResponseEntity<?> getAccountTransaction(@RequestHeader String Token, @RequestParam(name="AccID") UUID AccID, HttpServletRequest httpServletRequest) {

        String response = HttpRequestService.httpGetUserIDByToken(Token, httpServletRequest.getRemoteAddr());


        Integer resolvedUserId;


        try {
            // Try to parse the response to integer assuming it's a valid UserID
            resolvedUserId = Integer.parseInt(response);

            // If userID is valid, get transactions for the user
            List<Transaction> transactions = TransactionListService.getTransactionsByAccId(
                    DepositQueryService.getActiveAccountIdsByUserId(resolvedUserId),
                    DebitQueryService.getActiveAccountIdsByUserID(resolvedUserId));

            List<Transaction> userTransactions = new ArrayList<>();

            for(int i = 0; i < transactions.size(); i++){

                if(AccID.equals(transactions.get(i).getAccID())){

                    userTransactions.add(transactions.get(i));

                }

            }
            if(userTransactions.isEmpty()){
                return ResponseEntity.ok("no transactions found for this Account Id");
            }

            else{
                return ResponseEntity.ok(userTransactions);
            }


        } catch (NumberFormatException e) {
            // If parsing fails, it means the response was an error message
            return ResponseEntity.badRequest().body(response);  // Return the error message with status 400 Bad Request
        }
    }

    }





