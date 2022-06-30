package com.jlei.pointservice.services;

import com.jlei.pointservice.exceptions.UserPointsLowerThanZeroException;
import com.jlei.pointservice.models.Transaction;
import com.jlei.pointservice.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionService {


  private TransactionRepository transactionRepository;


  public void add(Transaction t) throws UserPointsLowerThanZeroException {
    if (t.getPoints() + getTotalPoints() < 0) {
      throw new UserPointsLowerThanZeroException();
    }
    transactionRepository.add(t);
  }

  public int getTotalPoints() {
    int count = 0;
    for (Transaction t : transactionRepository.getAll()) {
      count += t.getPoints();
    }
    return count;
  }

}
