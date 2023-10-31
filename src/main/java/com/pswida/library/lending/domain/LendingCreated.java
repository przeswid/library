package com.pswida.library.lending.domain;

import com.pswida.library.common.domain.DomainEvent;

class LendingCreated extends DomainEvent {
  public LendingCreated(LendingId lendingId, LendedBookCopyId lendedBookCopyId) {
  }
}
