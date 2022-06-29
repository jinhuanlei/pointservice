package com.jlei.pointservice.controllers;

import com.jlei.pointservice.services.PointService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PointController {

  private PointService pointService;

  @PostMapping("/points")
  public String points(@RequestBody int p) {
    return String.valueOf(p);
  }

  @GetMapping("/points")
  public String points() {
    return "";
  }
}
