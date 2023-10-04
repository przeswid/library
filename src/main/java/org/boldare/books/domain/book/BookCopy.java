package org.boldare.books.domain.book;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.boldare.books.domain.book.event.BookCopyBorrowed;
import org.boldare.books.domain.book.event.BookCopyReturned;
import org.boldare.books.domain.book.location.BookLocation;
import org.boldare.books.domain.book.location.Desk;
import org.boldare.books.domain.common.DomainEvent;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@ToString
public final class BookCopy {
  private static final int MAX_BORROW_DAYS = 30;

  private final String bookIsdn;

  private boolean isBorrowed;

  private OffsetDateTime borrowDate;

  private OffsetDateTime returnDate;

  private BookLocation location;

  private final List<DomainEvent> domainEvents = new ArrayList<>();

  public static BookCopy fromSnapshot(BookCopySnapshot bookCopySnapshot) {
    return new BookCopy(bookCopySnapshot.bookIsdn(), bookCopySnapshot.isBorrowed(), bookCopySnapshot.borrowDate(),
      bookCopySnapshot.returnDate(), bookCopySnapshot.location());
  }

  public BookCopySnapshot toSnapshot() {
    return new BookCopySnapshot(bookIsdn, isBorrowed, borrowDate, returnDate, location);
  }

  public void borrowBookCopy(OffsetDateTime borrowDate) {
    validateIfBookIsNotAlreadyBorrowed();
    this.isBorrowed = true;
    this.borrowDate = borrowDate;
    this.returnDate = this.borrowDate.plusDays(MAX_BORROW_DAYS);
    this.location = null;

    domainEvents.add(new BookCopyBorrowed(bookIsdn, bookIsdn));
  }

  public void returnBookCopy() {
    validateIfBookIsBorrowed();
    this.isBorrowed = false;
    this.borrowDate = null;
    this.returnDate = null;
    this.location = new Desk();

    domainEvents.add(new BookCopyReturned(bookIsdn, bookIsdn));
  }

  public List<DomainEvent> domainEvents() {
    return Collections.unmodifiableList(domainEvents);
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
