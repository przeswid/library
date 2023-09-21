package org.boldare.books.infrastructure;

import org.boldare.books.model.book.Book;
import org.boldare.books.model.book.BookRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class BookRepositoryInMemory implements BookRepository {

  public static final BookRepository INSTANCE = new BookRepositoryInMemory();

  private BookRepositoryInMemory() {
  }

  private final Set<Book> books = new HashSet<>();

  public void add(Book book) {
    books.add(book);
  }

  public Optional<Book> getByTitle(String title) {
    String titleLower = title.toLowerCase();
    return books.stream().filter(book -> book.getTitle().toLowerCase().equals(titleLower)).findFirst();
  }

  public Collection<Book> getAll() {
    return books;
  }

  public void removeAll() {
    books.clear();
  }
}
