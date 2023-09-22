package org.boldare.books;

import org.assertj.core.api.Assertions;
import org.boldare.books.infrastructure.BookRepositoryInMemory;
import org.boldare.books.model.book.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

class RentBookCopyTest {

  private final App app = new App();

  @BeforeEach
  void beforeEachTest() {
    BookRepositoryInMemory.INSTANCE.removeAll();
  }

  @Test
  void shouldBookCopyBeMarkedAsRented_whenSomeoneRentsIt() {
    // given
    String title = "My test book";
    String bookCopyId = "1";
    Book book = new Book(title, "1234567890", List.of("John Doe"));
    book.addBookCopy(bookCopyId);
    BookRepositoryInMemory.INSTANCE.add(book);

    ByteArrayInputStream in = new ByteArrayInputStream(title.getBytes());
    System.setIn(in);
    // when
    app.rentBookCopy(title, bookCopyId);
    // then
    Assertions.assertThat(BookRepositoryInMemory.INSTANCE.getByTitle(title)).isNotEmpty();
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

    BookRepositoryInMemory.INSTANCE.add(book);

    app.rentBookCopy(title, firstBookCopyId);
    app.rentBookCopy(title, secondBookCopyId);

    ByteArrayInputStream in = new ByteArrayInputStream(title.getBytes());
    System.setIn(in);
    // when
    List<Book> bookByTitle = app.findBookByTitle();
    // then
    Assertions.assertThat(bookByTitle).isEmpty();
  }
}
