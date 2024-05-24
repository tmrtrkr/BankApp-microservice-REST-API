package com.projects.app.bankApplication.Repository;
import com.projects.app.bankApplication.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

//Transaction Repository
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByAccId(UUID accId);
}
