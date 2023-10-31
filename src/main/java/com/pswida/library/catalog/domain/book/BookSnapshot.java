package com.pswida.library.catalog.domain.book;

import lombok.*;

import java.util.List;

@Builder
@Value
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BookSnapshot {
  @EqualsAndHashCode.Include
  BookIsbn isbn;
  String title;
  List<String> authors;
  BookCategory bookCategory;
  BookDiscussion discussion;
}
