package org.boldare.books.infrastructure;

import org.boldare.books.domain.book.Book;
import org.boldare.books.domain.book.BookRepository;

import java.util.*;

public final class BookRepositoryInMemory implements BookRepository {

  public static final BookRepository INSTANCE = new BookRepositoryInMemory();

  private BookRepositoryInMemory() {
  }

  private final Set<Book> books = new HashSet<>();

  public void add(Book book) {
    books.add(book);
  }

  public List<Book> searchByTitle(String title) {
    String titleLower = title.toLowerCase();
    return books.stream().filter(b -> hasTitle(b, titleLower)).filter(Book::hasAvailableCopy).toList();
  }

  public Optional<Book> getByTitle(String title) {
    return books.stream().filter(b -> b.getTitle().equals(title)).findFirst();
  }

  public Collection<Book> getAll() {
    return books;
  }

  public void removeAll() {
    books.clear();
  }

  private boolean hasTitle(Book book, String title) {
    return book.getTitle().toLowerCase().contains(title);
  }
}
