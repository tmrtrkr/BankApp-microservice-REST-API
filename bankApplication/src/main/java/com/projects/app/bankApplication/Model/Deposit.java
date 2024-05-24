package com.projects.app.bankApplication.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


@Entity
  @Table(name = "deposit")
  public class Deposit extends Account  {

    @Column(name = "UserID")
    private Integer UserID;

    @Id
    @Column(name = "AccID")
    private UUID AccID;

    @Column(name = "accounttype")
    private String AccType;

    @Column(name = "accstats")
    private Boolean AccStats;

    @Column(name = "Balance")
    private float Balance;

    @Column(name = "acccreationdate")
    private LocalDateTime AccCreationDate;



    public LocalDateTime getAccCreationDate() {
        return AccCreationDate;
    }

    public void setAccCreationDate(LocalDateTime accCreationDate) {
        AccCreationDate = accCreationDate;
    }

    public Integer getUserID() {
          return UserID;
      }

      public void setUserID(Integer userId) {
          UserID = userId;
      }

      public UUID getAccID() {
          return AccID;
      }

      public void setAccID(UUID accID) {
          AccID = accID;
      }

      public String getAccType() {
          return AccType;
      }

      public void setAccType(String accType) {
          AccType = accType;
      }

      public Boolean getAccStats() {
          return AccStats;
      }

      public void setAccStats(Boolean accStats) {
          AccStats = accStats;
      }

      public float getBalance() {
          return Balance;
      }

      public void setBalance(float balance) {
          Balance = balance;
      }


  }