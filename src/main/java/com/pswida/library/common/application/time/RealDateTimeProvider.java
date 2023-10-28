package com.pswida.library.common.application.time;

import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
class RealDateTimeProvider implements DateTimeProvider {
  @Override
  public OffsetDateTime getCurrentDateTime() {
    return OffsetDateTime.now();
  }
}
