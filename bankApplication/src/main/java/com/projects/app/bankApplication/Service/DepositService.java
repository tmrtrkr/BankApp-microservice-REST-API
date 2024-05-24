package com.projects.app.bankApplication.Service;

import com.projects.app.bankApplication.DataAccess.UserQueryService;
import com.projects.app.bankApplication.Model.Deposit;
import com.projects.app.bankApplication.Repository.DepositRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

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

    // Deposit Save with exception handling
    @Transactional
    public String saveDeposit(Integer userID, String accType, LocalDateTime dateTime) {

        try {

            Deposit deposit = new Deposit();

            UUID uuid = UUID.randomUUID();
            setAccID(uuid);


            deposit.setUserID(userID);
            deposit.setAccID(uuid);
            deposit.setAccStats(true);
            deposit.setBalance(0);
            deposit.setAccType(accType);
            deposit.setAccCreationDate(dateTime);
            depositRepository.save(deposit);

            EmailConfirmation(userID, accType);

            return accType + " Account created successfully.";
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            throw new RuntimeException("Failed to create deposit account for user ID: " + userID, e);
        }
    }

    // Preparing email for account confirmation with exception handling
    public void EmailConfirmation(Integer userID, String accType) {
        try {
            String emailMessage = "Your " + accType + " account has been created.\n" +
                    "AccID: " + getAccID() + "\n" +
                    "Fullname: " + query.getFullnameByUserID(userID) + " " + query.getSurnameByUserID(userID) + "\n" +
                    "Gender: " + query.getGenderByUserID(userID) + "\n" +
                    "Date of Birth: " + query.getBirthDateByUserID(userID) + "\n" +
                    "Phone Number: " + query.getPhoneByUserID(userID);

            emailSenderService.sendSimpleMail(query.getEmailByUserID(userID), accType + " Account Created", emailMessage);
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            throw new RuntimeException("Failed to send account confirmation email for user ID: " + userID , e);
        }
    }
}
