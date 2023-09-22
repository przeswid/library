package org.boldare.books.model.book;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.boldare.books.model.book.location.Desk;
import org.boldare.books.model.book.location.Location;
import org.boldare.books.model.book.location.Renter;

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

  private Location location;

  void rentIt(OffsetDateTime rentDate, String clientIdentifier) {
    validateIfBookIsNotAlreadyRent();
    this.isRent = true;
    this.rentDate = rentDate;
    this.returnDate = this.rentDate.plusDays(MAX_RENT_DAYS);
    this.location = new Renter(clientIdentifier);
  }

  void returnIt() {
    validateIfBookIsRent();
    this.isRent = false;
    this.rentDate = null;
    this.returnDate = null;
    this.location = new Desk();
  }

  private void validateIfBookIsRent() {
    if (!isRent) {
      throw new IllegalStateException("Book copy is not rent");
    }
  }

  boolean isRent() {
    return isRent;
  }

  private void validateIfBookIsNotAlreadyRent() {
    if (isRent) {
      throw new IllegalStateException("Book copy is already rent");
    }
  }

}
