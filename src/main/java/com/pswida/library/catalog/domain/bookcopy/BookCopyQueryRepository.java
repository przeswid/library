package com.pswida.library.catalog.domain.bookcopy;

import com.pswida.library.catalog.application.bookcopy.queries.GetBookCopyByIdResult;
import com.pswida.library.catalog.domain.book.BookIsbn;

import java.util.List;
import java.util.Optional;

public interface BookCopyQueryRepository {

  Optional<GetBookCopyByIdResult> getById(BookCopyId bookCopyId);

  List<BookCopy> getByBookIsbn(BookIsbn bookIsbn);
}
