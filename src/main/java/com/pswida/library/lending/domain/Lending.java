package com.pswida.library.lending.domain;

import java.time.Instant;

public class Lending {

  private final static long TWO_WEEKS_IN_SECONDS = 60 * 60 * 24 * 14;
  private final LendedBookCopyId lendedBookCopyId;

  private final Instant lendingDate;

  private final Instant expectedReturnDate;

  Lending(LendedBookCopyId lendedBookCopyId) {
    this.lendedBookCopyId = lendedBookCopyId;
    lendingDate = Instant.now();
    expectedReturnDate = lendingDate.plusSeconds(TWO_WEEKS_IN_SECONDS);
  }


}
