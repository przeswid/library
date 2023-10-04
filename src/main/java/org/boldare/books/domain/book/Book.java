package org.boldare.books.domain.book;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public final class Book {

  @EqualsAndHashCode.Include
  private final String isbn;
  private String title;
  private BookCategory bookCategory;
  private List<String> authors;
  private int availableCopies;

  Book(String title, String isbn, List<String> authors, BookCategory bookCategory, int availableCopies) {
    this.availableCopies = availableCopies;
    validateTitle(title);
    validateAuthors(authors);
    validateIsbn(isbn);

    this.isbn = isbn;
    this.authors = authors;
    this.title = title;
    this.bookCategory = bookCategory;
  }

  public static Book fromSnapshot(BookSnapshot snapshot) {
    return new Book(snapshot.title(), snapshot.isbn(), snapshot.authors(), snapshot.bookCategory(),snapshot.availableCopies());
  }

  public BookSnapshot toSnapshot() {
    return new BookSnapshot(isbn, title, authors, bookCategory, availableCopies);
  }

  public boolean hasAvailableCopy() {
    return availableCopies > 0;
  }

  public void increaseAvailableCopies() {
    availableCopies++;
  }

  public void decreaseAvailableCopies() {
    availableCopies--;
  }

  private void validateIsbn(String isbn) {
    if (isbn == null || isbn.isBlank()) {
      throw new IllegalArgumentException("ISBN cannot be blank");
    }
  }

  private void validateTitle(String title) {
    if (title == null || title.isBlank()) {
      throw new IllegalArgumentException("Title cannot be blank");
    }
  }

  private void validateAuthors(List<String> authors) {
    if (authors == null || authors.isEmpty()) {
      throw new IllegalArgumentException("Author cannot be blank");
    } else {
      authors.forEach(a -> {
        if (a == null || a.isBlank()) {
          throw new IllegalArgumentException("Author cannot be blank");
        }
      });
    }
  }

  private void validateBookCopyIdentifier(String bookCopyIdentifier) {
    if (bookCopyIdentifier == null || bookCopyIdentifier.isBlank()) {
      throw new IllegalArgumentException("Book copy identifier cannot be blank");
    }
  }
}

