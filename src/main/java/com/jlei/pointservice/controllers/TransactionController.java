package com.jlei.pointservice.controllers;

import com.jlei.pointservice.exceptions.PayerFirstTransactionLowerThanZero;
import com.jlei.pointservice.exceptions.PayerPointsLowerThanZeroException;
import com.jlei.pointservice.exceptions.ZeroPointTransactionException;
import com.jlei.pointservice.models.Transaction;
import com.jlei.pointservice.services.TransactionService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
public class TransactionController {

  private TransactionService transactionService;


  @PostMapping("/transactions")
  public void transactions(@Valid @RequestBody Transaction t) {
    try {
      transactionService.add(t);
    } catch (PayerPointsLowerThanZeroException e) {
      var reason = "Points for " + t.getPayer() + " can not lower than 0";
      var status = HttpStatus.FORBIDDEN;
      throw new ResponseStatusException(
          status, reason, e);
    } catch (PayerFirstTransactionLowerThanZero e) {
      var reason = "First transaction for " + t.getPayer() + " can not lower than 0";
      var status = HttpStatus.FORBIDDEN;
      throw new ResponseStatusException(
          status, reason, e);
    } catch (ZeroPointTransactionException e){
      var reason = "Points for transaction cannot be zero, its meaningless";
      var status = HttpStatus.FORBIDDEN;
      throw new ResponseStatusException(
          status, reason, e);
    }
  }
}
