package com.lafin.healthlink.domain.prescription;

import com.lafin.healthlink.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "prescription_items")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class PrescriptionItem extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private UUID timezoneLinkId;
  private UUID medicineId;
  private String medicineName;
  private float takeAmount;
  private float remainAmount;
  private float totalAmount;
  private String medicineUnit;
  @Column(columnDefinition = "TEXT")
  private String memo;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "prescription_id")
  private Prescription prescription;
}
