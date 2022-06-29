package com.jlei.pointservice.services;

import com.jlei.pointservice.exceptions.UserPointsLowerThanZeroException;
import com.jlei.pointservice.models.Transaction;
import com.jlei.pointservice.repositories.AvailableTransactionRepository;
import com.jlei.pointservice.repositories.PointRepository;
import com.jlei.pointservice.repositories.TransactionRepository;
import com.jlei.pointservice.utils.TransactionBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService implements ServiceInterface<Transaction> {


  private TransactionRepository transactionRepository;
  private AvailableTransactionRepository availableTransactionRepository;
  private PointRepository pointRepository;


  @Override
  public void add(Transaction t) throws UserPointsLowerThanZeroException {
    transactionRepository.add(t);
    if (t.getPoints() > 0) {
      availableTransactionRepository.add(TransactionBuilder.buildNewTransaction(t));
    } else if (pointRepository.get() + t.getPoints() < 0) {
      throw new UserPointsLowerThanZeroException();
    } else {
      var cur = -t.getPoints();
      while (cur > 0) {
        var earliestAvailableTransaction = availableTransactionRepository.peek();
        int earliestAvailablePoint = earliestAvailableTransaction.getPoints();
        if (earliestAvailablePoint <= cur) {
          var earliestTransaction = availableTransactionRepository.get();
          cur -= earliestTransaction.getPoints();
          transactionRepository.add(
              TransactionBuilder.buildNewNegativeTransaction(earliestTransaction));
        } else {
          earliestAvailableTransaction.setPoints(earliestAvailablePoint - cur);
        }
        cur -= earliestAvailablePoint;
      }
    }
    pointRepository.add(t.getPoints());
  }


}
