package org.boldare.books.domain.book.event;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.boldare.books.domain.common.DomainEvent;

@AllArgsConstructor
@Value
public class BookCopyReturned extends DomainEvent {
  private final String bookIsdn;
  private final String bookCopyId;
}
