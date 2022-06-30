package com.jlei.pointservice.controllers;

import com.jlei.pointservice.exceptions.TotalPointsLowerThanZeroException;
import com.jlei.pointservice.models.PayerList;
import com.jlei.pointservice.models.PointRequest;
import com.jlei.pointservice.services.PointService;
import com.jlei.pointservice.utils.DataBuilder;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
public class PointController {

  private PointService pointService;

  @PostMapping("/points")
  public PayerList points(@Valid @RequestBody PointRequest pointRequest) {
    try {
      return DataBuilder.buildPayerList(pointService.usePoints(pointRequest.getPoints()));
    } catch (TotalPointsLowerThanZeroException e) {
      var reason = "Points can not lower than 0";
      var status = HttpStatus.FORBIDDEN;
      throw new ResponseStatusException(
          status, reason, e);
    }
  }

  @GetMapping("/points")
  public PayerList points() {
    return DataBuilder.buildPayerList(pointService.getAllRemainingPoints());
  }
}
