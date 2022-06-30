package com.jlei.pointservice.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Payer {
  String payer;
  int points;
}
