package com.pswida.library.lending.infrastructure.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "lending_user")
@Data
public class LendingUserEntity {
  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  private String username;


}