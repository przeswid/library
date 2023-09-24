package org.boldare.books.domain.book;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public final class Book {

  @EqualsAndHashCode.Include
  private final String isbn;
  private String title;
  private BookCategory bookCategory;
  private List<String> authors;
  private Map<String, BookCopy> copies = new HashMap<>();

  Book(String title, String isbn, List<String> authors, BookCategory bookCategory) {
    validateTitle(title);
    validateAuthors(authors);
    validateIsbn(isbn);

    this.isbn = isbn;
    this.authors = authors;
    this.title = title;
    this.bookCategory = bookCategory;
  }

  Book(String title, String isbn, List<String> authors, BookCategory bookCategory, Map<String, BookCopy> copies) {
    this(title, isbn, authors, bookCategory);

    this.copies = copies;
  }

  public static Book fromSnapshot(BookSnapshot snapshot) {
    return new Book(snapshot.title(), snapshot.isbn(), snapshot.authors(), snapshot.bookCategory(), snapshot.copies()
      .entrySet()
      .stream()
      .collect(Collectors.toMap(Map.Entry::getKey, entry -> BookCopy.fromSnapshot(entry.getValue()))));
  }

  public BookSnapshot toSnapshot() {
    return new BookSnapshot(isbn, title, authors, bookCategory, copies.entrySet()
      .stream()
      .collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, entry -> entry.getValue().toSnapshot())));
  }

  public void addBookCopy(String bookCopyIdentifier) {
    validateBookCopyIdentifier(bookCopyIdentifier);
    copies.put(bookCopyIdentifier, new BookCopy(false, null, null));
  }

  public void borrowBookCopy(String bookCopyIdentifier, OffsetDateTime borrowDate) {
    copies.get(bookCopyIdentifier).borrowBook(borrowDate);
  }

  public boolean hasAvailableCopy() {
    return copies.entrySet().stream().map(Map.Entry::getValue).anyMatch(bookCopy -> !bookCopy.isBorrowed());
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

