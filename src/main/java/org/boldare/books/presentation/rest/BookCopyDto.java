package org.boldare.books.presentation.rest;

import java.time.OffsetDateTime;

record BookCopyDto(
  boolean isBorrowed,
  OffsetDateTime borrowDate,
  OffsetDateTime returnDate
) {
}
