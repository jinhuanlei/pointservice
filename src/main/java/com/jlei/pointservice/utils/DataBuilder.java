package com.jlei.pointservice.utils;

import com.jlei.pointservice.models.Payer;
import com.jlei.pointservice.models.Transaction;
import java.util.Date;
import java.util.Map.Entry;

public class DataBuilder {

  public static Transaction buildNewTransaction(Transaction t) {
    return Transaction.builder().points(t.getPoints()).payer(t.getPayer())
        .timestamp(new Date(System.currentTimeMillis())).build();
  }

  public static Payer buildPayer(Entry<String, Integer> entry) {
    return Payer.builder().payer(entry.getKey()).points(entry.getValue()).build();
  }
}
