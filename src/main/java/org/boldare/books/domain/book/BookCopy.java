package org.boldare.books.domain.book;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.boldare.books.domain.book.location.Desk;
import org.boldare.books.domain.book.location.Location;
import org.boldare.books.domain.book.location.Renter;

import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
final class BookCopy {

  private static final int MAX_BORROW_DAYS = 30;

  @EqualsAndHashCode.Include
  private final String id;

  private boolean isBorrowed;

  private OffsetDateTime borrowDate;

  private OffsetDateTime returnDate;

  private Location location;

  void borrowBook(OffsetDateTime borrowDate, String clientIdentifier) {
    validateIfBookIsNotAlreadyBorrowed();
    this.isBorrowed = true;
    this.borrowDate = borrowDate;
    this.returnDate = this.borrowDate.plusDays(MAX_BORROW_DAYS);
    this.location = new Renter(clientIdentifier);
  }

  void returnBook() {
    validateIfBookIsBorrowed();
    this.isBorrowed = false;
    this.borrowDate = null;
    this.returnDate = null;
    this.location = new Desk();
  }

  private void validateIfBookIsBorrowed() {
    if (!isBorrowed) {
      throw new IllegalStateException("Book copy is not borrowed");
    }
  }

  boolean isBorrowed() {
    return isBorrowed;
  }

  private void validateIfBookIsNotAlreadyBorrowed() {
    if (isBorrowed) {
      throw new IllegalStateException("Book copy is already borrowed");
    }
  }

}
