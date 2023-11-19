package com.pswida.library.catalog.infrastructure.db;

import com.pswida.library.catalog.domain.bookcopy.BookCopyId;
import com.pswida.library.catalog.domain.bookcopy.BookCopySnapshot;
import com.pswida.library.catalog.infrastructure.db.entity.BookCopyEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {BookMapper.class})
interface BookCopyMapper {

  BookCopyEntity mapBookCopySnapshotToBookCopyDocument(BookCopySnapshot bookCopySnapshot);

  BookCopySnapshot mapBookCopyDocumentToBookCopySnapshot(BookCopyEntity bookDocument);

  default BookCopyId mapBookCopyId(String isbn) {
    return new BookCopyId(isbn);
  }
  default String mapBookCopyIdId(BookCopyId bookCopyId) {
    return bookCopyId.id();
  }

}
