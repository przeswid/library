package org.boldare.books.commons;

import java.time.OffsetDateTime;

public interface DateTimeProvider {
  OffsetDateTime getCurrentDateTime();
}
