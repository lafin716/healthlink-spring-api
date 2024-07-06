package com.lafin.healthlink.app.medicine;

import com.lafin.healthlink.domain.medicine.Medicine;
import com.lafin.healthlink.domain.medicine.MedicineGateway;
import com.lafin.healthlink.domain.medicine.MedicineRepository;
import com.lafin.healthlink.domain.medicine.MedicineUseCase;
import com.lafin.healthlink.shared.util.ValidateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineService implements MedicineUseCase {
  private final MedicineRepository medicineRepository;
  private final MedicineGateway medicineGateway;

  @Override
  public List<Medicine> search(String medicineName) {
    if (ValidateUtil.isBlank(medicineName)) {
      return null;
    }

    var foundMeds = medicineRepository.findAllByMedicineNameIsContaining(medicineName);
    if (foundMeds.isEmpty()) {
      foundMeds = medicineGateway.search(medicineName);
      medicineRepository.saveAll(foundMeds);
    }

    return foundMeds;
  }
}
