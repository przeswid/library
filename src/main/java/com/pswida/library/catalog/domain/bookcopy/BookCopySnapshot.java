package com.pswida.library.catalog.domain.bookcopy;

import lombok.Builder;
import com.pswida.library.catalog.domain.book.BookIsbn;
import com.pswida.library.catalog.domain.bookcopy.location.BookLocation;

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
