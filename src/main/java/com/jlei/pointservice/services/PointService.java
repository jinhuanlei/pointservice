package com.jlei.pointservice.services;

import com.jlei.pointservice.repositories.PointRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PointService implements ServiceInterface<Integer> {

  private PointRepository pointRepository;

  @Override
  public void add(Integer integer) {
    pointRepository.add(integer);
  }
}
