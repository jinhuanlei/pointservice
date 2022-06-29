package com.jlei.pointservice.controllers;

import com.jlei.pointservice.exceptions.UserPointsLowerThanZeroException;
import com.jlei.pointservice.models.Transaction;
import com.jlei.pointservice.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TransactionController {

  private TransactionService transactionService;


  @PostMapping("/transactions")
  public String transactions(@RequestBody Transaction t) throws UserPointsLowerThanZeroException {
    transactionService.add(t);
    return t.toString();
  }
}
