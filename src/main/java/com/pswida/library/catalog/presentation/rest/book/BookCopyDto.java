package com.pswida.library.catalog.presentation.rest.book;

import com.pswida.library.catalog.domain.bookcopy.BookCopy;

import java.time.OffsetDateTime;

record BookCopyDto(
  String bookCopyId,
  BookCopy.Status status,
  String bookIsbn
) {
}
