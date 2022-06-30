package com.jlei.pointservice.models;

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PayerList {
  private List<Payer> payers;
}
