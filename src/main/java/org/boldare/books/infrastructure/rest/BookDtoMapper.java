package org.boldare.books.infrastructure.rest;

import org.boldare.books.domain.book.BookSnapshot;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface BookDtoMapper {
  BookDto mapBookSnapshotToBookDto(BookSnapshot bookSnapshot);
}
