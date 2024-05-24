package com.projects.app.bankApplication.Model;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Holder")
 public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Integer userID;

    @Column(name = "auth_token")
    private String authToken;

    @Column(name = "Email")
    private String email;

    @Column(name = "Fullname")
    private String fullname;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Pass")
    private String userPassword;


    @Column(name = "userbirthdate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date userbirthdate;


    @Column(name = "gender")
    private String gender;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getUserBirthDate() {
        return userbirthdate;
    }

    public void setUserBirthDate(Date userBirthDate) {
        this.userbirthdate = userBirthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}


