package org.boldare.books.infrastructure.rest;

import java.time.OffsetDateTime;

record BookCopyDto(
  boolean isBorrowed,
  OffsetDateTime borrowDate,
  OffsetDateTime returnDate
) {
}
