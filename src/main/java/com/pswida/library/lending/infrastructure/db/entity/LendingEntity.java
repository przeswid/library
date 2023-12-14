package com.pswida.library.lending.infrastructure.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(schema = "lending", name = "lending")
@Data
public class LendingEntity {
  @Id
  private String lendingId;
  private String lendedBookCopyId;
  private Instant lendingDate;
  private Instant expectedReturnDate;
}
