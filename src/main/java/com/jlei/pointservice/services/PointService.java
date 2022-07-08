package com.jlei.pointservice.services;

import static com.jlei.pointservice.utils.DataBuilder.buildPayedPoints;

import com.jlei.pointservice.exceptions.SpendPointsLowerThanZeroException;
import com.jlei.pointservice.exceptions.TotalPointsLowerThanZeroException;
import com.jlei.pointservice.models.Payer;
import com.jlei.pointservice.repositories.TransactionRepository;
import com.jlei.pointservice.utils.DataBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PointService {

  private TransactionRepository transactionRepository;

  public List<Payer> usePoints(Integer spent)
      throws TotalPointsLowerThanZeroException, SpendPointsLowerThanZeroException {
    if(spent < 0){
      throw new SpendPointsLowerThanZeroException();
    }
    if (transactionRepository.getTotalPoints() - spent < 0) {
      throw new TotalPointsLowerThanZeroException();
    }
    var list = transactionRepository.getAllAvailablePoints();
    var payerMap = new HashMap<String, Integer>();
    int index = 0;
    // loop through and deduct all availability points to match with point usage
    while (index < list.size() && spent > 0) {
      var p = list.get(index);
      assert p.getRemain() > 0;
      if (p.getRemain() >= spent) {
        addPointsToPayerMap(payerMap, p.getPayer(), -spent);
        // add negative transaction
        transactionRepository.add(DataBuilder.buildSpendingTransaction(p.getPayer(), -spent));
        p.setRemain(p.getRemain() - spent);
        spent = 0;
      } else {
        spent -= p.getRemain();
        addPointsToPayerMap(payerMap, p.getPayer(), -p.getRemain());
        //add negative transaction
        transactionRepository.add(
            DataBuilder.buildSpendingTransaction(p.getPayer(), -p.getRemain()));
        p.setRemain(0);
      }
      index++;
    }
    return buildPayedPoints(payerMap);
  }

  public void addPointsToPayerMap(Map<String, Integer> payerMap, String payer, int points) {
    if (payerMap.containsKey(payer)) {
      payerMap.put(payer, payerMap.get(payerMap) + points);
    }
    payerMap.put(payer, points);
  }

  public void createSpendingTransaction() {

  }

  public List<Payer> getAllRemainingPoints() {
    var map = transactionRepository.getPointsGroupByPayer();
    var list = new ArrayList<Payer>();
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      list.add(DataBuilder.buildPayer(entry));
    }
    return list;
  }
}
