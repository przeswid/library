package org.boldare.books.model.book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
  void add(Book book);
  List<Book> searchByTitle(String title);

  Optional<Book> getByTitle(String title);
  Iterable<Book> getAll();
  void removeAll();
}
