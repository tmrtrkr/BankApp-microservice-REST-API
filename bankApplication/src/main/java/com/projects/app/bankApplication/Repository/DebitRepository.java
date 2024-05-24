package com.projects.app.bankApplication.Repository;


import com.projects.app.bankApplication.Model.Account;
import com.projects.app.bankApplication.Model.Debit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public  interface DebitRepository extends JpaRepository<Debit,Long> {


    @Query("SELECT deb.AccID FROM Debit deb WHERE deb.UserID = :userId AND deb.AccStats = TRUE")
    ArrayList<UUID> findAccountIdsByUserId(@Param("userId") Integer userId);

    //Query for Account Object
    @Query("SELECT deb FROM Debit deb WHERE deb.UserID = :userId AND deb.AccStats = TRUE")
    ArrayList<Debit> findActiveAccountsByUserId(@Param("userId") Integer userId);

    
}
