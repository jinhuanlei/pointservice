package com.jlei.pointservice;

import com.jlei.pointservice.models.Transaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestData {

  public static List<Transaction> getTestTransactions() {
    List<Transaction> testTransactions = new ArrayList<>();
    Transaction t1 = Transaction.builder().points(100).payer("Payer1")
        .timestamp(new Date(1000)).build();
    Transaction t2 = Transaction.builder().points(100).payer("Payer2")
        .timestamp(new Date(2000)).build();
    Transaction t3 = Transaction.builder().points(100).payer("Payer3")
        .timestamp(new Date(3000)).build();
    testTransactions.add(t1);
    testTransactions.add(t2);
    testTransactions.add(t3);
    return testTransactions;
  }
}
