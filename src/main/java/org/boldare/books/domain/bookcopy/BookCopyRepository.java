package org.boldare.books.domain.bookcopy;

import org.boldare.books.domain.book.BookIsbn;

import java.util.List;

public interface BookCopyRepository {
  void add(BookCopy bookCopy);

  BookCopy getById(BookCopyId bookCopyId);

  List<BookCopy> getByBookIsbn(BookIsbn bookIsbn);
}
