package com.projects.app.bankApplication.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.projects.app.bankApplication.Model.Account;
import com.projects.app.bankApplication.Model.Transaction;
import com.projects.app.bankApplication.Service.*;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@RestController
public class AccountController {

    @Autowired
    private DebitService DebitService;

    @Autowired
    private DepositService DepositService;

    @Autowired
    private AccountsService AccountsService;


    @Autowired
    private com.projects.app.bankApplication.DataAccess.HttpRequestService HttpRequestService;

    @PostMapping(value="/OpenAccount")
    public String openAccount(@RequestHeader(name = "Token") String clientToken , @RequestBody JsonNode jsonNode, HttpServletRequest httpServletRequest) {
        System.out.println(clientToken);
        System.out.println(jsonNode);

        //Geting current Time
        LocalDateTime Time  = LocalDateTime.now();

        //Getting AccType from @Requestbody
        String accType = jsonNode.path("AccType").asText();

        //UserID will be set after Authentication response
        Integer userID;




        //Get Request for UserID
        String res = HttpRequestService.httpGetUserIDByToken(clientToken, httpServletRequest.getRemoteAddr());


        System.out.println(res);

        //Parsing UserID to Integer from Authentication response
        try { userID = Integer.parseInt(res); }
        catch (NumberFormatException e) {  return res ;  }



        //Checking if given account type is Deposit
        if(accType.equals("Deposit")) {

            //Saving Account to table Deposit
            DepositService.saveDeposit(userID , accType , Time);



            return "Deposit account has been created";

        }

        //Checking if given account type is Debit
        else if(accType.equals("Debit")) {

            //Saving Account to table Debit
            DebitService.saveDebit(userID , accType , Time);

            return "Debit account has been created";


        }

        //Account Type is not Debit or Deposit
        else {

            System.out.println(accType);


            return "Account Type is not Debit or Deposit";

        }

    }



    @GetMapping(value="/accountList")
    public ResponseEntity<?> accountList(@RequestHeader(name = "Token") String clientToken, HttpServletRequest httpServletRequest) {
        // Get UserID or error message from Authentication response
        String response = HttpRequestService.httpGetUserIDByToken(clientToken, httpServletRequest.getRemoteAddr());

        try {
            // Try to parse the response to integer assuming it's a valid UserID
            int userID = Integer.parseInt(response);

            // If successful, retrieve the merged account list and return it
            List<Account> accounts = AccountsService.getMergedAccountList(userID);
            return ResponseEntity.ok(accounts);  // Return the accounts list with status 200 OK
        } catch (NumberFormatException e) {
            // If parsing fails, it means the response was an error message
            return ResponseEntity.badRequest().body(response);  // Return the error message with status 400 Bad Request
        }
    }




    @GetMapping(value="/accountSummary")
    public ResponseEntity<?> accountSummary(@RequestHeader(name = "Token") String clientToken, @RequestParam UUID AccID, HttpServletRequest httpServletRequest) {
        // Get UserID or error message from Authentication response
        String response = HttpRequestService.httpGetUserIDByToken(clientToken, httpServletRequest.getRemoteAddr());

        try {
            // Try to parse the response to integer assuming it's a valid UserID
            int userID = Integer.parseInt(response);

            // If successful, retrieve the account by AccID and UserID
            Account account = AccountsService.findAccountByAccIDAndUserID(AccID, userID);
            return ResponseEntity.ok(account);  // Return the account with status 200 OK
        } catch (NumberFormatException e) {
            // If parsing fails, it means the response was an error message
            return ResponseEntity.badRequest().body("Invalid parameter provided: " + AccID );  // Return the error message with status 400 Bad Request
        }

    }


/*
    @GetMapping("accountSummary/Accounttransactions")
    public ResponseEntity<?> getUserTransactions(@RequestHeader String clientToken, @RequestParam UUID AccID, HttpServletRequest httpServletRequest) {

        // Get UserID or error message from Authentication response
        String response = HttpRequestService.httpGetUserIDByToken(clientToken, httpServletRequest.getRemoteAddr());
        Integer resolvedUserId;

        try {
            // Try to parse the response to integer assuming it's a valid UserID
            resolvedUserId = Integer.parseInt(response);


            // If userID is valid, get transactions for the user
            List<Transaction> transactions = TransactionListService.getTransactionsByAccId(
                    DepositQueryService.getActiveAccountIdsByUserId(resolvedUserId),
                    DebitQueryService.getActiveAccountIdsByUserID(resolvedUserId)  );

            return ResponseEntity.ok(transactions);  // Return the transactions list with status 200 OK
        } catch (NumberFormatException e) {
            // If parsing fails, it means the response was an error message
            return ResponseEntity.badRequest().body(response);  // Return the error message with status 400 Bad Request
        }
    }

*/
  /*  @GetMapping(value="/accountSummary")
    public List<Account>accountSummary(@RequestHeader(name = "accID") UUID accID){




    }  -/

   /* @PostMapping(value="/OpenAccount")
    public String accountSummary(@RequestHeader(name = "Token") String clientToken , @RequestBody JsonNode jsonNode, HttpServletRequest httpServletRequest){} */
}
