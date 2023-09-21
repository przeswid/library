package org.boldare.books.infrastructure;

import org.boldare.books.model.book.Book;
import org.boldare.books.model.book.BookRepository;

import java.util.*;

public final class BookRepositoryInMemory implements BookRepository {

  public static final BookRepository INSTANCE = new BookRepositoryInMemory();

  private BookRepositoryInMemory() {
  }

  private final Set<Book> books = new HashSet<>();

  public void add(Book book) {
    books.add(book);
  }

  public List<Book> getByTitle(String title) {
    String titleLower = title.toLowerCase();
    return books.stream().filter(book -> book.getTitle().toLowerCase().contains(titleLower)).toList();
  }

  public Collection<Book> getAll() {
    return books;
  }

  public void removeAll() {
    books.clear();
  }
}
