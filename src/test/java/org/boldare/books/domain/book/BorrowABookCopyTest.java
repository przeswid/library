package org.boldare.books.domain.book;

import org.assertj.core.api.Assertions;
import org.boldare.books.infrastructure.BookRepositoryInMemory;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

class BorrowABookCopyTest {

  @Test
  void shouldBookCopyBeMarkedAsBorrowed_whenSomeoneBorrowsIt() {
    // given
    String title = "My test book";
    String bookCopyId = "1";
    Book book = new Book(title, "1234567890", List.of("John Doe"), BookCategory.NOVEL);
    book.addBookCopy(bookCopyId);
    BookRepositoryInMemory.INSTANCE.add(book);

    // when
    book.borrowBookCopy(new Book.BorrowDataDto(bookCopyId, OffsetDateTime.now(), "John123"));
    // then
    Assertions.assertThat(book.hasAvailableCopy()).isFalse();
  }

  @Test
  void shouldNotFindABookByTitle_whenAllBookCopiesAreBorrowed() {
    // given
    String title = "My test book";
    String firstBookCopyId = "1";
    String secondBookCopyId = "2";
    Book book = new Book(title, "1234567890", List.of("John Doe"), BookCategory.NOVEL);
    book.addBookCopy(firstBookCopyId);
    book.addBookCopy(secondBookCopyId);
    // when
    book.borrowBookCopy(new Book.BorrowDataDto(firstBookCopyId, OffsetDateTime.now(), "John123"));
    book.borrowBookCopy(new Book.BorrowDataDto(secondBookCopyId, OffsetDateTime.now(), "John123"));
    // then
    Assertions.assertThat(book.hasAvailableCopy()).isFalse();
  }
}
