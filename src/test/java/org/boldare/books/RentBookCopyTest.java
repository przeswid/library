package org.boldare.books;

import org.assertj.core.api.Assertions;
import org.boldare.books.application.BookService;
import org.boldare.books.domain.book.Book;
import org.boldare.books.domain.book.BookRepository;
import org.boldare.books.infrastructure.BookRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

class RentBookCopyTest {

  private final BookRepository bookRepository = BookRepositoryInMemory.INSTANCE;

  private final BookService bookService = new BookService(bookRepository);

  @BeforeEach
  void beforeEachTest() {
    bookRepository.removeAll();
  }

  @Test
  void shouldBookCopyBeMarkedAsRented_whenSomeoneRentsIt() {
    // given
    String title = "My test book";
    String bookCopyId = "1";
    Book book = new Book(title, "1234567890", List.of("John Doe"));
    book.addBookCopy(bookCopyId);
    bookRepository.add(book);
    // when
    bookService.rentBookCopy(title, bookCopyId, "John123");
    // then
    Assertions.assertThat(bookRepository.getByTitle(title)).isNotEmpty();
  }

  @Test
  void shouldNotFindABookByTitle_whenAllBookCopiesAreRented() {
    // given
    String title = "My test book";
    String firstBookCopyId = "1";
    String secondBookCopyId = "2";
    Book book = new Book(title, "1234567890", List.of("John Doe"));
    book.addBookCopy(firstBookCopyId);
    book.addBookCopy(secondBookCopyId);

    bookRepository.add(book);

    bookService.rentBookCopy(title, firstBookCopyId, "john123");
    bookService.rentBookCopy(title, secondBookCopyId, "John123");
    // when
    Collection<Book> bookByTitle = bookService.findBookByTitle(title);
    // then
    Assertions.assertThat(bookByTitle).isEmpty();
  }
}
