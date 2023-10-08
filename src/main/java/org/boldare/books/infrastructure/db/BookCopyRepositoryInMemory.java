package org.boldare.books.infrastructure.db;

import org.boldare.books.domain.book.BookIsbn;
import org.boldare.books.domain.bookcopy.BookCopy;
import org.boldare.books.domain.bookcopy.BookCopyId;
import org.boldare.books.domain.bookcopy.BookCopyRepository;
import org.boldare.books.domain.bookcopy.BookCopySnapshot;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
final class BookCopyRepositoryInMemory implements BookCopyRepository {

  private final Set<BookCopySnapshot> bookCopySnapshots = new HashSet<>();

  @Override
  public void add(BookCopy bookCopy) {
    bookCopySnapshots.add(bookCopy.toSnapshot());
  }

  @Override
  public BookCopy getById(BookCopyId bookCopyId) {
    return bookCopySnapshots.stream()
      .filter(b -> b.bookCopyId().equals(bookCopyId))
      .findFirst()
      .map(BookCopy::fromSnapshot)
      .orElseThrow();
  }

  @Override
  public List<BookCopy> getByBookIsbn(BookIsbn bookIsbn) {
    return bookCopySnapshots.stream().filter(b -> b.bookIsbn().equals(bookIsbn)).map(BookCopy::fromSnapshot).toList();
  }
}
