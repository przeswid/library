package com.pswida.library.lending.domain;

import com.pswida.library.common.domain.DomainEvent;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Lending {

  private final static long TWO_WEEKS_IN_SECONDS = 60 * 60 * 24 * 14;

  private final LendingId lendingId;
  private final LendedBookCopyId lendedBookCopyId;

  private final Instant lendingDate;

  private final Instant expectedReturnDate;

  private final List<DomainEvent> domainEvents = new ArrayList<>();

  Lending(LendingId lendingId, LendedBookCopyId lendedBookCopyId) {
    this.lendingId = lendingId;
    this.lendedBookCopyId = lendedBookCopyId;
    lendingDate = Instant.now();
    expectedReturnDate = lendingDate.plusSeconds(TWO_WEEKS_IN_SECONDS);
  }

  public LendingSnapshot toSnapshot() {
    return new LendingSnapshot(lendingId, lendedBookCopyId, lendingDate, expectedReturnDate);
  }

  public static Lending createLending(LendingId lendingId, LendedBookCopyId lendedBookCopyId) {
    Lending lending = new Lending(lendingId, lendedBookCopyId);
    lending.domainEvents.add(new LendingCreated(lendingId, lendedBookCopyId));
    return lending;
  }

  public List<DomainEvent> domainEvents() {
    return Collections.unmodifiableList(domainEvents);
  }

}
