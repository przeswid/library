package org.boldare.books.model.book;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
final class BookCopy {

  private static final int MAX_RENT_DAYS = 30;

  @EqualsAndHashCode.Include
  private final String id;

  private boolean isRent;

  private OffsetDateTime rentDate;

  private OffsetDateTime returnDate;

  void rentIt(OffsetDateTime rentDate) {
    validateIfCanBeRent();
    this.isRent = true;
    this.rentDate = rentDate;
    this.returnDate = this.rentDate.plusDays(MAX_RENT_DAYS);
  }

  boolean isRent() {
    return isRent;
  }

  private void validateIfCanBeRent() {
    if (isRent) {
      throw new IllegalStateException("Book copy is already rent");
    }
  }

}
