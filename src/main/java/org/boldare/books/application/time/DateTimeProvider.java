package org.boldare.books.application.time;

import java.time.OffsetDateTime;

public interface DateTimeProvider {
  OffsetDateTime getCurrentDateTime();
}
