package com.jlei.pointservice.services;

import com.jlei.pointservice.exceptions.TotalPointsLowerThanZeroException;
import com.jlei.pointservice.models.Payer;
import com.jlei.pointservice.models.Point;
import com.jlei.pointservice.repositories.TransactionRepository;
import com.jlei.pointservice.utils.DataBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PointService {

  private TransactionRepository transactionRepository;

  public List<Payer> usePoints(Integer spent) throws TotalPointsLowerThanZeroException {
    if (transactionRepository.getTotalPoints() + spent < 0) {
      throw new TotalPointsLowerThanZeroException();
    }
    var list = transactionRepository.getAllAvailablePoints();
    var payerMap = new HashMap<String, Integer>();
    sort(list);
    int index = 0;

    while (index < list.size() && spent > 0) {
      var p = list.get(index);
      assert p.getRemain() > 0;
      if(p.getRemain() > spent){

      }
      index++;
    }
    return null;
  }

  public void createSpendingTransaction(){

  }

  public void sort(List<Point> list) {
    Collections.sort(list, (t1, t2) -> {
      if (t1.getTimestamp().before(t2.getTimestamp())) {
        return -1;
      } else if (t1.getTimestamp().after(t2.getTimestamp())) {
        return 1;
      } else {
        return 0;
      }
    });
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
