package org.boldare.books.model.book;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode
@Getter
public final class Book {

  private final String isbn;
  private final String title;
  private final List<String> authors;
  @Getter(AccessLevel.NONE)
  private final Set<BookCopy> copies = new HashSet<>();

  public Book(String title, String isbn, List<String> authors) {
    validateTitle(title);
    validateAuthors(authors);
    validateIsbn(isbn);

    this.isbn = isbn;
    this.authors = authors;
    this.title = title;
  }

  public void addBookCopy(String bookCopyIdentifier) {
    validateBookCopyIdentifier(bookCopyIdentifier);
    copies.add(new BookCopy(bookCopyIdentifier));
  }

  public void rentBookCopy(String bookCopyIdentifier, OffsetDateTime rentDate) {
    copies.stream()
      .filter(bookCopy -> bookCopy.getId().equals(bookCopyIdentifier))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException("Book copy not found"))
      .rentIt(rentDate);
  }

  public boolean hasAvailableCopy() {
    return copies.stream().anyMatch(bookCopy -> !bookCopy.isRent());
  }

  @Override
  public String toString() {
    return "Book{" + "title='" + title + '\'' + ", authors=" + authors + ", isbn='" + isbn + '\'' + '}';
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
