package com.pswida.library.catalog.domain.book;

import com.pswida.library.common.domain.tracker.ProcessTrackerId;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository {
  void save(Book book);

  Optional<Book> getByTitle(String title);

  Optional<Book> getByIsbn(BookIsbn isbn);

  Optional<Book> getByTrackerId(ProcessTrackerId trackerId);

  Collection<Book> getAll();
}
