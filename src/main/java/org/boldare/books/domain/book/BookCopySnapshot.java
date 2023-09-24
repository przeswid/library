package org.boldare.books.domain.book;

import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record BookCopySnapshot(
  boolean isBorrowed,
  OffsetDateTime borrowDate,
  OffsetDateTime returnDate
) {
}
