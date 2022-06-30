package com.jlei.pointservice.services;

import com.jlei.pointservice.exceptions.PayerFirstTransactionLowerThanZero;
import com.jlei.pointservice.exceptions.PayerPointsLowerThanZeroException;
import com.jlei.pointservice.exceptions.ZeroPointTransactionException;
import com.jlei.pointservice.models.Transaction;
import com.jlei.pointservice.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {


  private TransactionRepository transactionRepository;


  public void add(Transaction t)
      throws PayerPointsLowerThanZeroException, PayerFirstTransactionLowerThanZero, ZeroPointTransactionException {
    var pointsMap = transactionRepository.getPointsGroupByPayer();
    var payerTransactions = transactionRepository.getTransactionsByPayer(t.getPayer());
    if (t.getPoints() == 0) {
      throw new ZeroPointTransactionException();
    }

    var point = pointsMap.get(t.getPayer()) == null ? 0 : pointsMap.get(t.getPayer());
    if (t.getPoints() + point < 0) {
      throw new PayerPointsLowerThanZeroException();
    }
    // first transaction of payer cannot be negative
    if (payerTransactions.size() > 0 && t.getPoints() < 0 && t.getTimestamp()
        .before(payerTransactions.get(0).getTimestamp())) {
      throw new PayerFirstTransactionLowerThanZero();
    }

    transactionRepository.add(t);
  }

}
