package com.pswida.library.catalog.domain.bookcopy;

import java.util.Optional;

public interface BookCopyCommandRepository {
  void save(BookCopy bookCopy);

  Optional<BookCopy> getById(BookCopyId bookCopyId);
}
