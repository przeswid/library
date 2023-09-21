package org.boldare.books;

import org.assertj.core.api.Assertions;
import org.boldare.books.infrastructure.BookRepositoryInMemory;
import org.boldare.books.model.book.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

class FindBookByTitleTest {

  private final App app = new App();

  @BeforeEach
  void beforeEachTest() {
    BookRepositoryInMemory.INSTANCE.removeAll();
  }

  @Test
  void shouldFindMyTestBookByTitle_whenMyTestBookExists() {
    // given
    String title = "My test book";
    Book book = new Book(title, "1234567890", List.of("John Doe"));
    BookRepositoryInMemory.INSTANCE.add(book);

    ByteArrayInputStream in = new ByteArrayInputStream(title.getBytes());
    System.setIn(in);
    // when
    Optional<Book> bookByTitle = app.findBookByTitle();
    // then
    Assertions.assertThat(bookByTitle).contains(book);
  }

  @Test
  void shouldNotFindMyTestBookByTitle_whenMyTestBookDoesntExist() {
    // given
    String title = "My test book";
    ByteArrayInputStream in = new ByteArrayInputStream(title.getBytes());
    System.setIn(in);
    // when
    Optional<Book> bookByTitle = app.findBookByTitle();
    // then
    Assertions.assertThat(bookByTitle).isEmpty();
  }

}