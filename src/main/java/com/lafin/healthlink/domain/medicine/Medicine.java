package com.lafin.healthlink.domain.medicine;

import com.lafin.healthlink.shared.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "medicines")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Medicine extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String medicineName;
  private String itemSeq;
  private String companyName;
  @Column(columnDefinition = "TEXT")
  private String description;
  @Column(columnDefinition = "TEXT")
  private String usage;
  @Column(columnDefinition = "TEXT")
  private String effect;
  @Column(columnDefinition = "TEXT")
  private String sideEffect;
  @Column(columnDefinition = "TEXT")
  private String caution;
  @Column(columnDefinition = "TEXT")
  private String warning;
  @Column(columnDefinition = "TEXT")
  private String interaction;
  @Column(columnDefinition = "TEXT")
  private String keepMethod;
  private String appearance;
  private String colorClass1;
  private String colorClass2;
  private String pillImage;
  private String className;
  private String otcName;
  private String formCodeName;
}
