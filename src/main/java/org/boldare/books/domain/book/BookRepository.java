package org.boldare.books.domain.book;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository {
  void add(Book book);

  Collection<Book> searchByTitle(String title);

  Optional<Book> getByTitle(String title);

  Collection<Book> getAll();

  void removeAll();
}
