package org.boldare.books.model.book;

import java.util.Optional;

public interface BookCopyRepository {
  void add(BookCopy bookCopy);

  Optional<BookCopy> getById(String id);
}
