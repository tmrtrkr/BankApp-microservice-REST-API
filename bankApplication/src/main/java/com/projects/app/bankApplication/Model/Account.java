package com.projects.app.bankApplication.Model;

import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Account {


    private UUID AccID;

    private Integer userID;

    private String AccType;

    private Boolean AccStats;

    private float Balance;

    private LocalDateTime AccCreationDate;

    public LocalDateTime getAccCreationDate() {
        return AccCreationDate;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public UUID getAccID() {
        return AccID;
    }

    public void setAccID(UUID accID) {
        this.AccID = accID;
    }

    public String getAccType() {
        return AccType;
    }

    public void setAccType(String accType) {
        this.AccType = accType;
    }

    public Boolean getAccStats() {
        return AccStats;
    }

    public void setAccStats(Boolean accStats) {
        this.AccStats = accStats;
    }

    public void setAccCreationDate(LocalDateTime accCreationDate) {
        AccCreationDate = accCreationDate;
    }

    public float getBalance() {
        return Balance;
    }

    public void setBalance(float balance) {
        Balance = balance;
    }
}
