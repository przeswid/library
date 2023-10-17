package com.pswida.library.catalog.domain.bookcopy.event;

import lombok.AllArgsConstructor;
import lombok.Value;
import com.pswida.library.catalog.domain.core.DomainEvent;

@AllArgsConstructor
@Value
public class BookCopyReturned extends DomainEvent {
  private final String bookIsdn;
  private final String bookCopyId;
}
