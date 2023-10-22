package com.pswida.library.catalog.infrastructure.db;

import com.pswida.library.catalog.domain.book.Book;
import com.pswida.library.catalog.domain.book.BookIsbn;
import com.pswida.library.catalog.domain.book.BookRepository;
import com.pswida.library.catalog.domain.book.BookSnapshot;
import com.pswida.library.catalog.domain.bookcopy.BookCopy;
import com.pswida.library.catalog.domain.bookcopy.BookCopyRepository;
import com.pswida.library.common.domain.tracker.ProcessTrackerId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

import static java.util.function.Predicate.not;

@Repository
@AllArgsConstructor
final class BookRepositoryInMemory implements BookRepository {

  private final BookCopyRepository bookCopyRepository;

  private final Set<BookSnapshot> books = new HashSet<>();

  public void save(Book book) {
    books.add(book.toSnapshot());
  }

  public List<Book> searchByTitle(String title) {
    String titleLower = title.toLowerCase();
    return books.stream()
      .filter(b -> hasTitle(b, titleLower))
      .filter(b -> bookCopyRepository.getByBookIsbn(b.isbn())
        .stream()
        .map(BookCopy::toSnapshot)
        .anyMatch(not(bc -> bc.status() == BookCopy.Status.AVAILABLE)))
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

  @Override
  public Optional<Book> getByTrackerId(ProcessTrackerId trackerId) {
    return Optional.empty();
  }

  public Collection<Book> getAll() {
    return books.stream().map(Book::fromSnapshot).toList();
  }

  private boolean hasTitle(BookSnapshot bookSnapshot, String title) {
    return bookSnapshot.title().toLowerCase().contains(title);
  }
}
