package com.pswida.library.catalog.infrastructure.db;

import com.pswida.library.catalog.domain.book.BookIsbn;
import com.pswida.library.catalog.domain.bookcopy.BookCopy;
import com.pswida.library.catalog.domain.bookcopy.BookCopyId;
import com.pswida.library.catalog.domain.bookcopy.BookCopyRepository;
import com.pswida.library.catalog.domain.bookcopy.BookCopySnapshot;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
class BookCopyRepositoryInMemory implements BookCopyRepository {

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
