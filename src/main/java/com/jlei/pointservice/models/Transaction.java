package com.jlei.pointservice.models;

import java.util.Date;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
public class Transaction {

  @NotNull
  Date timestamp;
  @NotNull
  String payer;
  @NotNull
  int points;
}
