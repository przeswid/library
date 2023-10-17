package com.pswida.library.catalog.presentation.rest;

import com.pswida.library.catalog.domain.book.BookCategory;

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
