package com.pswida.library.lending.infrastructure.services;

record BookCopyDto(
  String bookCopyId,
  BookCopyStatus status,
  String bookIsbn
) {
}
