package org.boldare.books.application.time;

import java.time.OffsetDateTime;

public class RealDateTimeProvider implements DateTimeProvider {
  @Override
  public OffsetDateTime getCurrentDateTime() {
    return OffsetDateTime.now();
  }
}
