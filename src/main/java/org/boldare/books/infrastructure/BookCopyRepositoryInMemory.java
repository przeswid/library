package org.boldare.books.infrastructure;

import org.boldare.books.model.book.BookCopy;
import org.boldare.books.model.book.BookCopyRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class BookCopyRepositoryInMemory implements BookCopyRepository {

  public static final BookCopyRepository INSTANCE = new BookCopyRepositoryInMemory();

  private BookCopyRepositoryInMemory() {

  }

  private final Set<BookCopy> bookCopies = new HashSet<>();

  public void add(BookCopy bookCopy) {
    bookCopies.add(bookCopy);
  }

  public Optional<BookCopy> getById(String id) {
    return bookCopies.stream().filter(bookCopy -> bookCopy.getId().equals(id)).findFirst();
  }

}
