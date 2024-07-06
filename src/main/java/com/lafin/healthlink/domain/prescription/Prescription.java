package com.lafin.healthlink.domain.prescription;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lafin.healthlink.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "prescriptions")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Prescription extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private UUID userId;
  private String prescriptionName;
  private String hospitalName;
  private int takeDays;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate startedAt;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate finishedAt;
  @Column(columnDefinition = "TEXT")
  private String memo;

  @OneToMany(mappedBy = "prescription", fetch = FetchType.EAGER)
  private List<PrescriptionItem> items;
}
