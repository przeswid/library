package org.boldare.books.model.book;

import java.util.List;

public interface BookRepository {
  void add(Book book);
  List<Book> getByTitle(String title);
  Iterable<Book> getAll();
  void removeAll();
}
