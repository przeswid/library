package com.pswida.library.catalog.domain.bookcopy.event;

import com.pswida.library.catalog.domain.book.BookIsbn;
import com.pswida.library.catalog.domain.bookcopy.BookCopyId;
import com.pswida.library.catalog.domain.core.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class BookCopyAdded extends DomainEvent {
  private final BookIsbn bookIsdn;
  private final BookCopyId bookCopyId;
}
