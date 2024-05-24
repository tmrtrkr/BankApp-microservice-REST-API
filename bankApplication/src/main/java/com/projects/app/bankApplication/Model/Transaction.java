package com.projects.app.bankApplication.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "transactionlist")
public class Transaction {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transactionid")
    private UUID TransactionID;

    @Column(name = "accid")
    private UUID accId;


    @Column(name="amount")
    private float Amount;

    @Column(name="shortdes")
    private String ShortDes;

    @Column(name="transactiondate")
    private LocalDateTime TransactionDate;


    public UUID getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(UUID transactionID) {
        TransactionID = transactionID;
    }

    
    public LocalDateTime getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        TransactionDate = transactionDate;
    }


    public UUID getAccID() {
        return accId;
    }

    public void setAccID(UUID accID) {
        accId = accID;
    }


    public float getAmount() {
        return Amount;
    }

    public void setAccStats(float amount) {
        Amount = amount;
    }

    public String getShortDes() {
        return ShortDes;
    }

    public void setShortDes(String shortDes) {
        ShortDes = shortDes;
    }


}