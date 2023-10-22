package com.pswida.library.catalog.domain.bookcopy;

import com.pswida.library.catalog.domain.book.BookIsbn;
import com.pswida.library.catalog.domain.bookcopy.location.BookLocation;
import lombok.Builder;

@Builder
public record BookCopySnapshot(
  BookIsbn bookIsbn,
  BookCopyId bookCopyId,
  BookCopy.Status status,
  BookLocation location
) {
  public BookCopySnapshot(BookIsbn bookIsbn, BookCopyId bookCopyId) {
    this(bookIsbn, bookCopyId, BookCopy.Status.AVAILABLE, null);
  }
}
