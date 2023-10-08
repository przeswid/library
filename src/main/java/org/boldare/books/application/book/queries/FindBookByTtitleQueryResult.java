package org.boldare.books.application.book.queries;

import org.boldare.books.application.core.cqs.query.QueryResult;
import org.boldare.books.domain.book.BookIsbn;

public record FindBookByTtitleQueryResult(
  BookIsbn bookIsbn,
  String bookTitle
) implements QueryResult {
}
