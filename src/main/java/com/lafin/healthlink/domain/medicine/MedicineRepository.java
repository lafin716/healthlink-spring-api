package com.lafin.healthlink.domain.medicine;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MedicineRepository extends JpaRepository<Medicine, UUID> {
  List<Medicine> findAllByMedicineNameIsContaining(String medicineName);
}
