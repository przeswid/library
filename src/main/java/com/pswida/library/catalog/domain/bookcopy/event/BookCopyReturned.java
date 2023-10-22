package com.pswida.library.catalog.domain.bookcopy.event;

import com.pswida.library.common.domain.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class BookCopyReturned extends DomainEvent {
  private final String bookIsdn;
  private final String bookCopyId;
}
