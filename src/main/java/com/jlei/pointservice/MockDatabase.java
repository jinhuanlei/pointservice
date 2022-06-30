package com.jlei.pointservice;

import com.jlei.pointservice.models.Transaction;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * Mock Database for querying and storing the data
 */
@Getter
@Component
public class MockDatabase {

  private List<Transaction> transactionsDbInstance;

  public MockDatabase() {
    transactionsDbInstance = new ArrayList<>();
  }
}
