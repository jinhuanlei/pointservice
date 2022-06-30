package com.jlei.pointservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jlei.pointservice.models.Transaction;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = PointServiceApplication.class,
    webEnvironment = WebEnvironment.RANDOM_PORT)
public class TransactionsIntegrationTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;


  @Test
  public void testAddTransaction() {
    Transaction t = Transaction.builder().points(5).payer("Payer1")
        .timestamp(new Date(System.currentTimeMillis())).build();
    ResponseEntity<String> responseEntity = this.restTemplate
        .postForEntity("http://localhost:" + port + "/transactions", t, String.class);
    assertEquals(200, responseEntity.getStatusCodeValue());
  }

  @Test
  public void testAddFirstNegativeTransaction() {
    Transaction t = Transaction.builder().points(-5).payer("Payer1")
        .timestamp(new Date(999)).build();
    ResponseEntity<String> responseEntity = this.restTemplate
        .postForEntity("http://localhost:" + port + "/transactions", t, String.class);
    assertEquals(403, responseEntity.getStatusCodeValue());
  }
}
