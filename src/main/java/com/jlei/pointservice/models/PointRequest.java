package com.jlei.pointservice.models;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PointRequest {

  @NotNull Integer points;
}
