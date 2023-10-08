package org.boldare.books.domain.bookcopy;

import lombok.Builder;
import org.boldare.books.domain.book.BookIsbn;
import org.boldare.books.domain.bookcopy.location.BookLocation;

import java.time.OffsetDateTime;

@Builder
public record BookCopySnapshot(
  BookIsbn bookIsbn,
  BookCopyId bookCopyId,
  boolean isBorrowed,
  OffsetDateTime borrowDate,
  OffsetDateTime returnDate,
  BookLocation location
) {
  public BookCopySnapshot(BookIsbn bookIsbn, BookCopyId bookCopyId) {
    this(bookIsbn, bookCopyId, false, null, null, null);
  }
}
