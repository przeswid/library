package com.pswida.library.catalog.domain.book;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository {
  void add(Book book);

  Optional<Book> getByTitle(String title);

  Optional<Book> getByIsbn(BookIsbn isbn);

  Collection<Book> getAll();
}
