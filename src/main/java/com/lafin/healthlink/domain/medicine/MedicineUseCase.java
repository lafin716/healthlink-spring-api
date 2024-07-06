package com.lafin.healthlink.domain.medicine;

import java.util.List;

public interface MedicineUseCase {
  List<Medicine> search(String medicineName);
}
