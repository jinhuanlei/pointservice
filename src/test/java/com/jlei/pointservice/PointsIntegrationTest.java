package com.jlei.pointservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jlei.pointservice.models.PayerList;
import com.jlei.pointservice.models.PointRequest;
import com.jlei.pointservice.models.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(classes = PointServiceApplication.class,
    webEnvironment = WebEnvironment.RANDOM_PORT)
class PointsIntegrationTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @BeforeEach
  void init() {
    var list = TestData.getTestTransactions();
    for (Transaction t : list) {
      this.restTemplate
          .postForEntity("http://localhost:" + port + "/transactions", t, String.class);
    }
  }

  @Test
  public void testGetPoints() {
    var pl = this.restTemplate.getForObject("http://localhost:" + port + "/points",
        PayerList.class);
    assertEquals(3, pl.getPayers().size());
  }

  @Test
  public void testSpendPoints() {
    var pq = new PointRequest();
    pq.setPoints(100);
    var responseEntity = this.restTemplate
        .postForEntity("http://localhost:" + port + "/points", pq, PayerList.class);
    assertEquals(200, responseEntity.getStatusCodeValue());
    assertEquals(1, responseEntity.getBody().getPayers().size());
  }

  @Test
  public void testSpendNegativePoints() {
    var pq = new PointRequest();
    pq.setPoints(5000);
    var responseEntity = this.restTemplate
        .postForEntity("http://localhost:" + port + "/points", pq, PayerList.class);
    assertEquals(403, responseEntity.getStatusCodeValue());
  }

}
