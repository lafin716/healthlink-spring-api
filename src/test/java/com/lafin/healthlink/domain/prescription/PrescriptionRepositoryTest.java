package com.lafin.healthlink.domain.prescription;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:application-test.yml")
class PrescriptionRepositoryTest {

  @Autowired
  private PrescriptionRepository prescriptionRepository;

  @Test
  void test() {
    var pres = prescriptionRepository.findAll();

    pres.forEach((prescription -> {
      var items = prescription.getItems();
      items.forEach((item -> {
        System.out.println(item.getMedicineName());
      }));
    }));
  }
}