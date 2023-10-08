package org.boldare.books.application.core.time;

import java.time.OffsetDateTime;

public interface DateTimeProvider {
  OffsetDateTime getCurrentDateTime();
}
