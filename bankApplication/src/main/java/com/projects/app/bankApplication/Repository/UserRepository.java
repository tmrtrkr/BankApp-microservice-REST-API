package com.projects.app.bankApplication.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.projects.app.bankApplication.Model.User;

import java.util.Optional;
import java.util.UUID;


public  interface  UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByAuthToken(String authToken);

    Optional<User> findByUserID(Integer userID);


    boolean existsByEmail(String email);


}
