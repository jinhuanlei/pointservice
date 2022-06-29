package com.jlei.pointservice.repositories;

import com.jlei.pointservice.MockDatabase;
import com.jlei.pointservice.models.Transaction;
import java.util.PriorityQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AvailableTransactionRepository implements RepositoryInterface<Transaction> {

  private MockDatabase mockDatabase;
  private PriorityQueue<Transaction> availableTransactionsDbInstance;

  @Autowired
  public AvailableTransactionRepository(MockDatabase mockDatabase) {
    this.mockDatabase = mockDatabase;
    availableTransactionsDbInstance = mockDatabase.getAvailableTransactionsDbInstance();
  }

  @Override
  public void add(Transaction transaction) {
    availableTransactionsDbInstance.offer(transaction);
    System.out.printf("Available Transaction added %s, current size: %d \n",
        transaction, availableTransactionsDbInstance.size());
  }

  public Transaction peek() {
    return availableTransactionsDbInstance.peek();
  }

  public Transaction get() {
    return availableTransactionsDbInstance.poll();
  }
}
