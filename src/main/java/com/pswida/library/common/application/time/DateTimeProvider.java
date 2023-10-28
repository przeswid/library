package com.pswida.library.common.application.time;

import java.time.OffsetDateTime;

public interface DateTimeProvider {
  OffsetDateTime getCurrentDateTime();
}
