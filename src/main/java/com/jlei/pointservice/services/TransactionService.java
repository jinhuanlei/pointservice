package com.jlei.pointservice.services;

import com.jlei.pointservice.exceptions.PayerPointsLowerThanZeroException;
import com.jlei.pointservice.models.Transaction;
import com.jlei.pointservice.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {


  private TransactionRepository transactionRepository;


  public void add(Transaction t) throws PayerPointsLowerThanZeroException {
    var pointsMap = transactionRepository.getPointsByPayer();
    var point = pointsMap.get(t.getPayer()) == null ? 0 : pointsMap.get(t.getPayer());
    if (t.getPoints() + point < 0) {
      throw new PayerPointsLowerThanZeroException();
    }
    transactionRepository.add(t);
  }

}
