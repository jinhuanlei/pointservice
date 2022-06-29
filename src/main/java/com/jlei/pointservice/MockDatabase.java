package com.jlei.pointservice;

import com.jlei.pointservice.models.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * Mock Database for querying and storing the data
 */
@Getter
@Component
public class MockDatabase {

  private Integer[] remainPointsDbInstance;
  private List<Transaction> allTransactionsDbInstance;
  // Store transactions with usable points
  private PriorityQueue<Transaction> availableTransactionsDbInstance;

  public MockDatabase() {
    remainPointsDbInstance = new Integer[]{0};
    allTransactionsDbInstance = new ArrayList<>();
    // Create a PriorityQueue for selecting most current transaction in O(1) time complexity
    availableTransactionsDbInstance = new PriorityQueue<>((t1, t2) -> {
      if (t1.getTimestamp().before(t2.getTimestamp())) {
        return -1;
      } else if (t1.getTimestamp().after(t2.getTimestamp())) {
        return 1;
      } else {
        return 0;
      }
    });
  }
}
