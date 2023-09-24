package org.boldare.books.infrastructure;

import org.boldare.books.domain.book.Book;
import org.boldare.books.domain.book.BookCopySnapshot;
import org.boldare.books.domain.book.BookRepository;
import org.boldare.books.domain.book.BookSnapshot;

import java.util.*;
import java.util.function.Predicate;

public final class BookRepositoryInMemory implements BookRepository {

  public static final BookRepository INSTANCE = new BookRepositoryInMemory();

  private BookRepositoryInMemory() {
  }

  private final Set<BookSnapshot> books = new HashSet<>();

  public void add(Book book) {
    books.add(book.toSnapshot());
  }

  public List<Book> searchByTitle(String title) {
    String titleLower = title.toLowerCase();
    return books.stream()
      .filter(b -> hasTitle(b, titleLower))
      .filter(b -> b.copies()
        .entrySet()
        .stream()
        .map(Map.Entry::getValue)
        .anyMatch(Predicate.not(BookCopySnapshot::isBorrowed)))
      .map(Book::fromSnapshot)
      .toList();
  }

  public Optional<Book> getByTitle(String title) {
    return books.stream().filter(b -> b.title().equals(title)).findFirst().map(Book::fromSnapshot);
  }

  public Collection<Book> getAll() {
    return books.stream().map(Book::fromSnapshot).toList();
  }

  private boolean hasTitle(BookSnapshot bookSnapshot, String title) {
    return bookSnapshot.title().toLowerCase().contains(title);
  }
}
