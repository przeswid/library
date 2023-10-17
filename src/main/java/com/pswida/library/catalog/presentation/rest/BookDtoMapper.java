package com.pswida.library.catalog.presentation.rest;

import com.pswida.library.catalog.application.book.queries.FindBookByTtitleQueryResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface BookDtoMapper {

  @Mapping(target = "isbn", source = "bookIsbn.isbn")
  @Mapping(target = "title", source = "bookTitle")
  BookDto mapBookResultToBookDto(FindBookByTtitleQueryResult result);
}
