package org.boldare.books.domain.bookcopy.event;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.boldare.books.domain.book.BookIsbn;
import org.boldare.books.domain.bookcopy.BookCopyId;
import org.boldare.books.domain.core.DomainEvent;

@AllArgsConstructor
@Value
public class BookCopyAdded extends DomainEvent {
  private final BookIsbn bookIsdn;
  private final BookCopyId bookCopyId;
}
