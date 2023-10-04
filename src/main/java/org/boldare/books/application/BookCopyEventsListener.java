package org.boldare.books.application;

import lombok.AllArgsConstructor;
import org.boldare.books.domain.book.event.BookCopyAddedToLibrary;
import org.boldare.books.domain.book.event.BookCopyBorrowed;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class BookCopyEventsListener {

  private final BookService bookService;

  @EventListener
  public void onApplicationEvent(BookCopyAddedToLibrary event) {
    String bookIsdn = event.getBookIsdn();
    bookService.increaseAvailableCopies(bookIsdn);
  }

  @EventListener
  public void onApplicationEvent(BookCopyBorrowed event) {
    String bookIsdn = event.getBookIsdn();
    bookService.decreaseAvailableCopies(bookIsdn);
  }

}
