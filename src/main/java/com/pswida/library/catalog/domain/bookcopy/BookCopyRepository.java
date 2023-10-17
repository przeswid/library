package com.pswida.library.catalog.domain.bookcopy;

import com.pswida.library.catalog.domain.book.BookIsbn;

import java.util.List;

public interface BookCopyRepository {
  void add(BookCopy bookCopy);

  BookCopy getById(BookCopyId bookCopyId);

  List<BookCopy> getByBookIsbn(BookIsbn bookIsbn);
}
