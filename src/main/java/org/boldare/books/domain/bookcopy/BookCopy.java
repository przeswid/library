package org.boldare.books.domain.bookcopy;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.boldare.books.domain.book.BookIsbn;
import org.boldare.books.domain.bookcopy.event.BookCopyBorrowed;
import org.boldare.books.domain.bookcopy.location.BookLocation;
import org.boldare.books.domain.core.DomainEvent;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@ToString
public final class BookCopy {
  private static final int MAX_BORROW_DAYS = 30;

  private final BookIsbn bookIsbn;

  private final BookCopyId bookCopyId;

  private boolean isBorrowed;

  private OffsetDateTime borrowDate;

  private OffsetDateTime returnDate;

  private BookLocation location;

  private final List<DomainEvent> domainEvents = new ArrayList<>();

  public static BookCopy fromSnapshot(BookCopySnapshot bookCopySnapshot) {
    return new BookCopy(bookCopySnapshot.bookIsbn(), bookCopySnapshot.bookCopyId(), bookCopySnapshot.isBorrowed(), bookCopySnapshot.borrowDate(),
      bookCopySnapshot.returnDate(), bookCopySnapshot.location());
  }

  public BookCopySnapshot toSnapshot() {
    return new BookCopySnapshot(bookIsbn, bookCopyId, isBorrowed, borrowDate, returnDate, location);
  }

  public void borrowBookCopy(OffsetDateTime borrowDate) {
    validateIfBookIsNotAlreadyBorrowed();
    this.isBorrowed = true;
    this.borrowDate = borrowDate;
    this.returnDate = this.borrowDate.plusDays(MAX_BORROW_DAYS);
    this.location = null;

    domainEvents.add(new BookCopyBorrowed(bookIsbn, bookCopyId));
  }

  public List<DomainEvent> domainEvents() {
    return Collections.unmodifiableList(domainEvents);
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
