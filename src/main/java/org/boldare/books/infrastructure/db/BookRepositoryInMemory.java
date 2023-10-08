package org.boldare.books.infrastructure.db;

import lombok.AllArgsConstructor;
import org.boldare.books.domain.book.Book;
import org.boldare.books.domain.book.BookIsbn;
import org.boldare.books.domain.book.BookRepository;
import org.boldare.books.domain.book.BookSnapshot;
import org.boldare.books.domain.bookcopy.BookCopy;
import org.boldare.books.domain.bookcopy.BookCopyRepository;
import org.boldare.books.domain.bookcopy.BookCopySnapshot;
import org.springframework.stereotype.Repository;

import java.util.*;

import static java.util.function.Predicate.not;

@Repository
@AllArgsConstructor
final class BookRepositoryInMemory implements BookRepository {

  private final BookCopyRepository bookCopyRepository;

  private final Set<BookSnapshot> books = new HashSet<>();

  public void add(Book book) {
    books.add(book.toSnapshot());
  }

  public List<Book> searchByTitle(String title) {
    String titleLower = title.toLowerCase();
    return books.stream()
      .filter(b -> hasTitle(b, titleLower))
      .filter(b -> bookCopyRepository.getByBookIsbn(b.isbn())
        .stream()
        .map(BookCopy::toSnapshot)
        .anyMatch(not(BookCopySnapshot::isBorrowed)))
      .map(Book::fromSnapshot)
      .toList();
  }

  public Optional<Book> getByTitle(String title) {
    return books.stream().filter(b -> b.title().equals(title)).findFirst().map(Book::fromSnapshot);
  }

  @Override
  public Optional<Book> getByIsbn(BookIsbn isbn) {
    return books.stream().filter(b -> b.isbn().equals(isbn)).findFirst().map(Book::fromSnapshot);
  }

  public Collection<Book> getAll() {
    return books.stream().map(Book::fromSnapshot).toList();
  }

  private boolean hasTitle(BookSnapshot bookSnapshot, String title) {
    return bookSnapshot.title().toLowerCase().contains(title);
  }
}
