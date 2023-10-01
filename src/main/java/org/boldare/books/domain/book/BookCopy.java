package org.boldare.books.domain.book;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.boldare.books.domain.book.location.BookLocation;
import org.boldare.books.domain.book.location.Desk;

import java.time.OffsetDateTime;

@AllArgsConstructor
@ToString
final class BookCopy {
  private static final int MAX_BORROW_DAYS = 30;

  private boolean isBorrowed;

  private OffsetDateTime borrowDate;

  private OffsetDateTime returnDate;

  private BookLocation location;

  static BookCopy fromSnapshot(BookCopySnapshot bookCopySnapshot) {
    return new BookCopy(bookCopySnapshot.isBorrowed(),
      bookCopySnapshot.borrowDate(), bookCopySnapshot.returnDate(), bookCopySnapshot.location());
  }

  BookCopySnapshot toSnapshot() {
    return new BookCopySnapshot(isBorrowed, borrowDate, returnDate, location);
  }

  void borrowBook(OffsetDateTime borrowDate) {
    validateIfBookIsNotAlreadyBorrowed();
    this.isBorrowed = true;
    this.borrowDate = borrowDate;
    this.returnDate = this.borrowDate.plusDays(MAX_BORROW_DAYS);
    this.location = null;
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
