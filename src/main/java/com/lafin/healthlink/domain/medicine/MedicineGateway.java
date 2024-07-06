package com.lafin.healthlink.domain.medicine;

import java.util.List;

public interface MedicineGateway {
  List<Medicine> search(String medicineName);
}
