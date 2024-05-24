package com.Projects.app.Authorization.Model;


import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "Holder")
public class AuthenticationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "auth_token")
    private String authToken;

    @Column(name = "authstats")
    private Boolean authStats;

    @Column(name = "authip")
    private String authIP;

    @Column(name = "tokenCreationDate")
    private LocalDateTime tokenCreationDate;


    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }


    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }



    public boolean isAuthStats() {
        return authStats;
    }

    public void setAuthStats(boolean authStats) {
        this.authStats = authStats;
    }

    public String getAuthIP() {
        return authIP;
    }

    public void setAuthIP(String authIP) {
        this.authIP = authIP;
    }


    public LocalDateTime getTokenCreationDate() {
        return tokenCreationDate;
    }

    public void setTokenCreationDate(LocalDateTime tokenCreationDate) {
        this.tokenCreationDate = tokenCreationDate;
    }

}
