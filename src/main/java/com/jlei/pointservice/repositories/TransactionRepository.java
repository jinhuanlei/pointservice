package com.jlei.pointservice.repositories;

import com.jlei.pointservice.MockDatabase;
import com.jlei.pointservice.models.Point;
import com.jlei.pointservice.models.Transaction;
import java.util.ArrayList;
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

  public Map<String, Integer> getPointsGroupByPayer() {
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

  public List<Point> getAllAvailablePoints(){
    var res = new ArrayList<Point>();
    var map = getPointsGroupByPayer();
    for(Map.Entry<String, Integer> e : map.entrySet()){
      res.addAll(getAvailablePointsByPayer(e.getKey()));
    }
    return res;
  }

  public List<Point> getAvailablePointsByPayer(String payer) {
    var payerTransactions = getTransactionsByPayer(payer);
    List<Point> temp = new ArrayList<>();
    for (Transaction t : payerTransactions) {
      if (temp.size() == 0 || t.getPoints() > 0) {
        var p = Point.builder().points(t.getPoints()).payer(t.getPayer()).remain(t.getPoints())
            .timestamp(t.getTimestamp()).build();
        temp.add(p);
      } else if (t.getPoints() == 0) {
        throw new RuntimeException("Transaction should not contain zero points");
      } else if (t.getPoints() < 0) {
        assert temp.size() > 0;
        int cur = t.getPoints();
        int index = 0;
        while (index < temp.size() && cur < 0) {
          Point p = temp.get(index);
          assert p.getRemain() > 0;
          if (p.getRemain() + cur >= 0) {
            p.setRemain(p.getRemain() + cur);
            break;
          } else {
            cur += p.getRemain();
            p.setRemain(0);
          }
          index++;
        }
      }
    }
    List<Point> res = new ArrayList<>();
    for (Point p : temp) {
      if (p.getRemain() > 0) {
        res.add(p);
      }
    }
    return res;
  }

  public List<Transaction> getTransactionsByPayer(String payer) {
    var list = new ArrayList<Transaction>();
    var transactions = getAllTransaction();
    for (Transaction t : transactions) {
      if (t.getPayer().equals(payer)) {
        list.add(t);
      }
    }
    return list;
  }
}
