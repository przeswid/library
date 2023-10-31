package com.pswida.library.catalog.presentation.rest.book;

import com.pswida.library.catalog.application.book.queries.FindBookByTtitleQueryResult;
import com.pswida.library.catalog.domain.book.BookIsbn;
import com.pswida.library.catalog.domain.book.BookSnapshot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
interface BookDtoMapper {

  @Mapping(target = "isbn", source = "bookIsbn.isbn")
  @Mapping(target = "title", source = "bookTitle")
  BookDto mapBookResultToBookDto(FindBookByTtitleQueryResult result);

  @Mapping(target = "isbn", source = "book", qualifiedByName = "mapIsbn")
  BookSnapshot mapBookDtoToBookSnapshot(BookDto book);

  @Named("mapIsbn")
  default BookIsbn mapIsbn(BookDto bookDto) {
    return new BookIsbn(bookDto.isbn());
  }

}
