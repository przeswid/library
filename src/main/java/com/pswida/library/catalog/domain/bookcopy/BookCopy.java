package com.pswida.library.catalog.domain.bookcopy;

import com.pswida.library.catalog.domain.book.BookIsbn;
import com.pswida.library.catalog.domain.bookcopy.location.BookLocation;
import com.pswida.library.common.domain.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@ToString
public final class BookCopy {
  private static final int MAX_BORROW_DAYS = 30;

  private final BookIsbn bookIsbn;

  private final BookCopyId bookCopyId;

  private Status status;

  private BookLocation location;

  private final List<DomainEvent> domainEvents = new ArrayList<>();

  public static BookCopy fromSnapshot(BookCopySnapshot bookCopySnapshot) {
    return new BookCopy(bookCopySnapshot.bookIsbn(), bookCopySnapshot.bookCopyId(), bookCopySnapshot.status(),
      bookCopySnapshot.location());
  }

  public BookCopySnapshot toSnapshot() {
    return new BookCopySnapshot(bookIsbn, bookCopyId, status, location);
  }

  public List<DomainEvent> domainEvents() {
    return Collections.unmodifiableList(domainEvents);
  }

  public enum Status {
    AVAILABLE, BORROWED, RESERVED, LOST
  }
}
