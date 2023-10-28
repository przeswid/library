package com.pswida.library.catalog.infrastructure.db;

import com.pswida.library.catalog.domain.book.BookIsbn;
import com.pswida.library.catalog.domain.book.BookSnapshot;
import com.pswida.library.catalog.infrastructure.db.document.BookDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface BookMapper {

  BookDocument mapBookSnapshotToBookDocument(BookSnapshot bookSnapshot);

  BookSnapshot mapBookDocumentToBookSnapshot(BookDocument bookDocument);

  default BookIsbn mapIsbn(String isbn) {
    return new BookIsbn(isbn);
  }

  default String mapIsbn(BookIsbn bookIsbn) {
    return bookIsbn.isbn();
  }

}
