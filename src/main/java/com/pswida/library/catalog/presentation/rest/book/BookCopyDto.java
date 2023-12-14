package com.pswida.library.catalog.presentation.rest.book;

import com.pswida.library.catalog.domain.bookcopy.BookCopy;

record BookCopyDto(
  String bookCopyId,
  BookCopy.Status status,
  String bookIsbn
) {
}
