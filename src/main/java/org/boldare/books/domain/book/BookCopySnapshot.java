package org.boldare.books.domain.book;

import lombok.Builder;
import org.boldare.books.domain.book.location.BookLocation;

import java.time.OffsetDateTime;

@Builder
public record BookCopySnapshot(
  String bookIsdn,

//  String bookCopyId,
  boolean isBorrowed,
  OffsetDateTime borrowDate,
  OffsetDateTime returnDate,
  BookLocation location
) {
}
