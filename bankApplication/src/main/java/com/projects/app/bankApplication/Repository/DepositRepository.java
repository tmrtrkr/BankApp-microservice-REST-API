package com.projects.app.bankApplication.Repository;


import com.projects.app.bankApplication.Model.Account;
import com.projects.app.bankApplication.Model.Debit;
import com.projects.app.bankApplication.Model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public  interface DepositRepository extends JpaRepository<Deposit,Long> {

    @Query("SELECT dep.AccID FROM Deposit dep WHERE dep.UserID = :userId AND dep.AccStats = TRUE")
    ArrayList<UUID> findActiveAccountIdsByUserId(@Param("userId") Integer userId);


    //Query for Account Object
    @Query("SELECT dep FROM Deposit dep WHERE dep.UserID = :userId AND dep.AccStats = TRUE")
    ArrayList<Deposit> findActiveAccountsByUserId(@Param("userId") Integer userId);



}
