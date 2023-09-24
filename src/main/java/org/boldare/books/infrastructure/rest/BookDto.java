package org.boldare.books.infrastructure.rest;

import org.boldare.books.domain.book.BookCategory;

import java.util.List;
import java.util.Map;

record BookDto(
  String isbn,
  String title,
  List<String> authors,
  BookCategory bookCategory,
  Map<String, BookCopyDto> copies
) {

}
