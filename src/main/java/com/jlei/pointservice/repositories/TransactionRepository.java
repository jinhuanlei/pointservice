package com.jlei.pointservice.repositories;

import com.jlei.pointservice.MockDatabase;
import com.jlei.pointservice.models.Transaction;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepository {

  private MockDatabase mockDatabase;
  private List<Transaction> transactionsDbInstance;

  @Autowired
  public TransactionRepository(MockDatabase mockDatabase) {
    this.mockDatabase = mockDatabase;
    transactionsDbInstance = mockDatabase.getTransactionsDbInstance();
  }

  public void add(Transaction transaction) {

    transactionsDbInstance.add(transaction);
    Collections.sort(transactionsDbInstance, (t1, t2) -> {
      if (t1.getTimestamp().before(t2.getTimestamp())) {
        return -1;
      } else if (t1.getTimestamp().after(t2.getTimestamp())) {
        return 1;
      } else {
        return 0;
      }
    });
    System.out.printf("Transaction added: %s, current size: %d \n", transaction,
        transactionsDbInstance.size());
  }

  public List<Transaction> getAllTransaction() {
    return transactionsDbInstance;
  }


  public int getTotalPoints() {
    int count = 0;
    for (Transaction t : transactionsDbInstance) {
      count += t.getPoints();
    }
    return count;
  }

  public Map<String, Integer> getPointsByPayer() {
    var map = new HashMap<String, Integer>();
    var transactions = getAllTransaction();
    for (Transaction t : transactions) {
      var key = t.getPayer();
      if (map.containsKey(key)) {
        map.put(key, map.get(key) + t.getPoints());
      } else {
        map.put(key, t.getPoints());
      }
    }
    return map;
  }
}
