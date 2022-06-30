package com.jlei.pointservice.controllers;

import com.jlei.pointservice.exceptions.TotalPointsLowerThanZeroException;
import com.jlei.pointservice.models.Payer;
import com.jlei.pointservice.models.PointRequest;
import com.jlei.pointservice.services.PointService;
import java.util.List;
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
  public List<Payer> points(@Valid @RequestBody PointRequest pointRequest) {
    try {
      return pointService.usePoints(pointRequest.getPoints());
    } catch (TotalPointsLowerThanZeroException e) {
      var reason = "Points can not lower than 0";
      var status = HttpStatus.FORBIDDEN;
      throw new ResponseStatusException(
          status, reason, e);
    }
  }

  @GetMapping("/points")
  public List<Payer> points() {
    return pointService.getAllRemainingPoints();
  }
}
