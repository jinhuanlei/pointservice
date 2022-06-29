package com.jlei.pointservice.repositories;

import com.jlei.pointservice.MockDatabase;
import com.jlei.pointservice.models.Transaction;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepository implements
    RepositoryInterface<Transaction> {

  private MockDatabase mockDatabase;
  private List<Transaction> allTransactionsDbInstance;

  @Autowired
  public TransactionRepository(MockDatabase mockDatabase) {
    this.mockDatabase = mockDatabase;
    allTransactionsDbInstance = mockDatabase.getAllTransactionsDbInstance();
  }

  @Override
  public void add(Transaction transaction) {

    allTransactionsDbInstance.add(transaction);
    System.out.printf("Transaction added: %s, current size: %d \n", transaction,
        allTransactionsDbInstance.size());
  }


}
