package com.jlei.pointservice.repositories;

import com.jlei.pointservice.MockDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PointRepository implements RepositoryInterface<Integer> {

  private MockDatabase mockDatabase;
  private Integer[] pointsDbInstance;

  @Autowired
  public PointRepository(MockDatabase mockDatabase) {
    this.mockDatabase = mockDatabase;
    pointsDbInstance = mockDatabase.getRemainPointsDbInstance();
  }

  @Override
  public void add(Integer integer) {
    pointsDbInstance[0] += integer;
    //TODO: remove
    System.out.format("Point Added: %d , current point: %d \n", integer, pointsDbInstance[0]);
  }

  public Integer get(){
    return pointsDbInstance[0];
  }
}
