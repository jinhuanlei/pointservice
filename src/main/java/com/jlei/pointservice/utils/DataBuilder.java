package com.jlei.pointservice.utils;

import com.jlei.pointservice.models.Payer;
import com.jlei.pointservice.models.Transaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DataBuilder {

  public static Transaction buildNewTransaction(Transaction t) {
    return Transaction.builder().points(t.getPoints()).payer(t.getPayer())
        .timestamp(new Date(System.currentTimeMillis())).build();
  }

  public static Transaction buildSpendingTransaction(String payer, int points) {
    return Transaction.builder().points(points).payer(payer)
        .timestamp(new Date(System.currentTimeMillis())).build();
  }

  public static List<Payer> buildPayedPoints(Map<String, Integer> hm) {
    List<Payer> res = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : hm.entrySet()) {
      res.add(Payer.builder().payer(entry.getKey()).points(entry.getValue()).build());
    }
    return res;
  }

  public static Payer buildPayer(Entry<String, Integer> entry) {
    return Payer.builder().payer(entry.getKey()).points(entry.getValue()).build();
  }
}
