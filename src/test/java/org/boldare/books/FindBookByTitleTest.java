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

class FindBookByTitleTest {

  private final BookRepository bookRepository = BookRepositoryInMemory.INSTANCE;

  private final BookService bookService = new BookService(bookRepository);

  @BeforeEach
  void beforeEachTest() {
    bookRepository.removeAll();
  }

  @Test
  void shouldFindMyTestBookByTitle_whenMyTestBookExists() {
    // given
    String title = "My test book";
    Book book = new Book(title, "1234567890", List.of("John Doe"));
    book.addBookCopy("1");
    bookRepository.add(book);

    // when
    Collection<Book> bookByTitle = bookService.findBookByTitle(title);
    // then
    Assertions.assertThat(bookByTitle).contains(book);
  }

  @Test
  void shouldFindTwoBooksThatContainLetterA_whenTwoBooksMatchExpression() {
    // given
    Book daVinciBook = new Book("The Da Vinci Code", "1234567890", List.of("John Doe"));
    daVinciBook.addBookCopy("1");
    Book theHistoryBook = new Book("A History of Adventure", "1234567890", List.of("John Doe"));
    theHistoryBook.addBookCopy("1");
    bookRepository.add(daVinciBook);
    bookRepository.add(theHistoryBook);

    // when
    Collection<Book> bookByTitle = bookService.findBookByTitle("a");
    // then
    Assertions.assertThat(bookByTitle).containsExactlyInAnyOrder(daVinciBook, theHistoryBook);
  }

  @Test
  void shouldNotFindMyTestBookByTitle_whenMyTestBookDoesntExist() {
    // given
    String title = "My test book";
    // when
    Collection<Book> bookByTitle = bookService.findBookByTitle(title);
    // then
    Assertions.assertThat(bookByTitle).isEmpty();
  }

}