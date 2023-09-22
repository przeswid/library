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

  public void rentBookCopy(String title, String bookCopyId, String clientIdentifier) {
    Book book = bookRepository.getByTitle(title).orElseThrow(() -> new IllegalStateException("Book not found"));

    Book.RentDataDto rentData = new Book.RentDataDto(bookCopyId, realDateTimeProvider.getCurrentDateTime(),
      clientIdentifier);
    book.rentBookCopy(rentData);
  }

  public Collection<Book> findAllBooks() {
    return bookRepository.getAll();
  }

}
