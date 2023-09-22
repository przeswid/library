package org.boldare.books.commons;

import java.time.OffsetDateTime;

public class RealDateTimeProvider implements DateTimeProvider {
  @Override
  public OffsetDateTime getCurrentDateTime() {
    return OffsetDateTime.now();
  }
}
