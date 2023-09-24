package org.boldare.books.domain.book;

import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record BookSnapshot(
  String isbn,
  String title,
  List<String> authors,
  BookCategory bookCategory,
  Map<String, BookCopySnapshot> copies
) {
}
