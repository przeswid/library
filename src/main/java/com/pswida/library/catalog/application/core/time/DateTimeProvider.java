package com.pswida.library.catalog.application.core.time;

import java.time.OffsetDateTime;

public interface DateTimeProvider {
  OffsetDateTime getCurrentDateTime();
}
