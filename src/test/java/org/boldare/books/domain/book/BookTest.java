package org.boldare.books.domain.book;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

class BookTest {

  @Nested
  class BorrowBook {
    @Test
    void shouldBookCopyBeMarkedAsBorrowed_whenSomeoneBorrowsIt() {
      // given
      String title = "My test book";
      String bookCopyId = "1";
      Book book = new Book(title, "1234567890", List.of("John Doe"), BookCategory.NOVEL);
      book.addBookCopy(bookCopyId);

      // when
      book.borrowBookCopy(bookCopyId, OffsetDateTime.now());
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
      book.borrowBookCopy(firstBookCopyId, OffsetDateTime.now());
      book.borrowBookCopy(secondBookCopyId, OffsetDateTime.now());
      // then
      Assertions.assertThat(book.hasAvailableCopy()).isFalse();
    }
  }

  @Nested
  class SnapshotMapping {

    @Test
    void shouldMapBookToSnapshot() {
      // given
      String title = "My test book";
      String bookCopyId = "1";
      Book book = new Book(title, "1234567890", List.of("John Doe"), BookCategory.NOVEL);
      book.addBookCopy(bookCopyId);
      // when
      BookSnapshot bookSnapshot = book.toSnapshot();
      // then
      Assertions.assertThat(bookSnapshot.title()).isEqualTo(title);
      Assertions.assertThat(bookSnapshot.isbn()).isEqualTo("1234567890");
      Assertions.assertThat(bookSnapshot.authors()).containsExactly("John Doe");
      Assertions.assertThat(bookSnapshot.bookCategory()).isEqualTo(BookCategory.NOVEL);
      Assertions.assertThat(bookSnapshot.copies()).hasSize(1);
      Assertions.assertThat(bookSnapshot.copies().get(bookCopyId)).isNotNull();
    }
  }
}
