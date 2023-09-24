package org.boldare.books.application;

import lombok.AllArgsConstructor;
import org.boldare.books.application.time.DateTimeProvider;
import org.boldare.books.application.time.RealDateTimeProvider;
import org.boldare.books.domain.book.Book;
import org.boldare.books.domain.book.BookRepository;

import java.util.Collection;

@AllArgsConstructor
public class BookService {

  private final BookRepository bookRepository;

  private final DateTimeProvider realDateTimeProvider = new RealDateTimeProvider();

  public Collection<Book> findBookByTitle(String title) {
    return bookRepository.searchByTitle(title);
  }

  public void borrowBookCopy(String title, String bookCopyId) {
    Book book = bookRepository.getByTitle(title).orElseThrow(() -> new IllegalStateException("Book not found"));
    book.borrowBookCopy(bookCopyId, realDateTimeProvider.getCurrentDateTime());
  }

  public Collection<Book> findAllBooks() {
    return bookRepository.getAll();
  }

}
