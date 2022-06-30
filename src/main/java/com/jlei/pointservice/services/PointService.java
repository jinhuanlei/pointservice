package com.jlei.pointservice.services;

import com.jlei.pointservice.exceptions.TotalPointsLowerThanZeroException;
import com.jlei.pointservice.models.Payer;
import com.jlei.pointservice.repositories.TransactionRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PointService {

  private TransactionRepository transactionRepository;

  public void usePoints(Integer integer) throws TotalPointsLowerThanZeroException {
    if (transactionRepository.getTotalPoints() + integer < 0) {
      throw new TotalPointsLowerThanZeroException();
    }
  }

  public List<Payer> getAllRemainingPoints() {
    return null;
  }
}
