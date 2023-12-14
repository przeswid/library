package com.pswida.library.catalog.infrastructure.db;

import com.pswida.library.catalog.domain.book.BookIsbn;
import com.pswida.library.catalog.domain.book.BookSnapshot;
import com.pswida.library.catalog.infrastructure.db.entity.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface BookMapper {

  BookEntity mapBookSnapshotToBookDocument(BookSnapshot bookSnapshot);

  BookSnapshot mapBookDocumentToBookSnapshot(BookEntity bookEntity);

  default BookIsbn mapIsbn(String isbn) {
    return new BookIsbn(isbn);
  }

  default String mapIsbn(BookIsbn bookIsbn) {
    return bookIsbn.isbn();
  }

}
