package com.jlei.pointservice.models;

import java.util.Date;
import lombok.Data;

@Data
public class Transaction {
  Date timestamp;
  String payer;
  int points;
}
