package org.boldare.books.domain.book;

import lombok.Builder;

import java.util.List;

@Builder
public record BookSnapshot(
  BookIsbn isbn,
  String title,
  List<String> authors,
  BookCategory bookCategory,

  int availableCopies
) {
}
