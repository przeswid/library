package com.pswida.library.catalog.application.core.time;

import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
class RealDateTimeProvider implements DateTimeProvider {
  @Override
  public OffsetDateTime getCurrentDateTime() {
    return OffsetDateTime.now();
  }
}
