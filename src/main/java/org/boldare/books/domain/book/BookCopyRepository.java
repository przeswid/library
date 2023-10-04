package org.boldare.books.domain.book;

public interface BookCopyRepository {
  void add(BookCopy bookCopy);

  BookCopy getById(String bookCopyId);
}
