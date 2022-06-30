package com.jlei.pointservice.models;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Point {
  Date timestamp;
  String payer;
  int points;
  int remain;
}
