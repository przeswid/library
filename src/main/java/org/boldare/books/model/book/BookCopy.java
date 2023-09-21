package org.boldare.books.model.book;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public final class BookCopy {

  private final String id;

  private final Book book;

}
