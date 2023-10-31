package com.pswida.library.lending.domain;

import java.time.Instant;

public record LendingSnapshot(
  LendingId lendingId,
  LendedBookCopyId lendedBookCopyId,
  Instant lendingDate,
  Instant expectedReturnDate
) {
}
