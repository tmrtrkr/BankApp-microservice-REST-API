package com.projects.app.bankApplication.Service;

import com.projects.app.bankApplication.DataAccess.UserQueryService;
import com.projects.app.bankApplication.Model.Debit;
import com.projects.app.bankApplication.Repository.DebitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class DebitService {

    @Autowired
    private DebitRepository debitRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UserQueryService query;

       private UUID accID;

     public UUID getAccID() {
          return accID;
      }

      public void setAccID(UUID accID) {
         this.accID = accID;
      }

    // Debit Save with exception handling
    public String saveDebit(Integer userID, String accType, LocalDateTime datetime) {
        try {

            UUID uuid = UUID.randomUUID();
            setAccID(uuid);

            Debit debit = new Debit();
            debit.setUserID(userID);
            debit.setAccID(uuid);
            debit.setAccStats(true);
            debit.setBalance(0);
            debit.setAccType(accType);
            debit.setAccCreationDate(datetime);
            debitRepository.save(debit);

            EmailConfirmation(userID, accType);

            return accType + " Account created successfully.";
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            throw new RuntimeException("Failed to create account for user ID: " + userID, e);
        }
    }

    // Preparing email for account confirmation with exception handling
    public void EmailConfirmation(Integer userID, String accType) {
        try {
            String emailMessage = "Your " + accType + " account has been created.\nAccID: " + getAccID() +
                    "\nFullname: " + query.getFullnameByUserID(userID) + " " + query.getSurnameByUserID(userID) +
                    "\nGender: " + query.getGenderByUserID(userID) +
                    "\nDate of Birth: " + query.getBirthDateByUserID(userID) +
                    "\nPhone Number: " + query.getPhoneByUserID(userID);

            emailSenderService.sendSimpleMail(query.getEmailByUserID(userID), "Debit Account Created", emailMessage);
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            throw new RuntimeException("Failed to send account confirmation email for user ID: " + userID, e);
        }
    }

    public ArrayList<UUID> getAccountIdsByUserId(Integer userId) {
        try {
            return debitRepository.findAccountIdsByUserId(userId);
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            throw new RuntimeException("Failed to retrieve account IDs for user ID: " + userId, e);
        }
    }
}
