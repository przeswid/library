package com.pswida.library.common.domain;

import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public abstract class DomainEvent {

  private final OffsetDateTime eventDate;

  protected DomainEvent() {
    this.eventDate = OffsetDateTime.now();
  }
}
