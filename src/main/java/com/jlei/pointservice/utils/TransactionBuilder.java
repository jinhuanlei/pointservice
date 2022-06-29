package com.jlei.pointservice.utils;

import com.jlei.pointservice.models.Transaction;
import java.util.Date;

public class TransactionBuilder {

  public static Transaction buildNewNegativeTransaction(Transaction t) {
    Transaction cr = new Transaction();
    cr.setPayer(t.getPayer());
    cr.setPoints(-t.getPoints());
    cr.setTimestamp(new Date(System.currentTimeMillis()));
    return cr;
  }

  public static Transaction buildNewTransaction(Transaction t) {
    Transaction cr = new Transaction();
    cr.setPayer(t.getPayer());
    cr.setPoints(t.getPoints());
    cr.setTimestamp(new Date(System.currentTimeMillis()));
    return cr;
  }
}
