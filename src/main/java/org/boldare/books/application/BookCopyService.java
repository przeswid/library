package org.boldare.books.application;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.boldare.books.application.time.DateTimeProvider;
import org.boldare.books.domain.book.*;
import org.boldare.books.domain.book.event.BookCopyAddedToLibrary;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookCopyService {

  private final BookCopyRepository bookCopyRepository;

  private final BookRepository bookRepository;

  private final DateTimeProvider realDateTimeProvider;

  private final ApplicationEventPublisher eventPublisher;

  public void borrow(String bookCopyId) {
    BookCopy bookCopy = bookCopyRepository.getById(bookCopyId);
    bookCopy.borrowBookCopy(realDateTimeProvider.getCurrentDateTime());
    bookCopy.domainEvents().forEach(eventPublisher::publishEvent);
  }

  public void createBookCopy(String bookIsbn, String bookCopyId) {
    bookRepository.getByIsbn(bookIsbn).orElseThrow(() -> new RuntimeException("Book not found"));

    BookCopy bookCopy = BookCopy.fromSnapshot(new BookCopySnapshot(bookIsbn, false, null, null, null));
    bookCopyRepository.add(bookCopy);
    eventPublisher.publishEvent(new BookCopyAddedToLibrary(bookIsbn, bookCopyId));
  }

  @PostConstruct
  private void initDatabase() {
    createBookCopy("978-0261103252", "1");
  }
}
