package com.pswida.library.catalog.application.book.queries;

import com.pswida.library.catalog.application.core.cqs.query.QueryResult;
import com.pswida.library.catalog.domain.book.BookIsbn;

public record FindBookByTtitleQueryResult(
  BookIsbn bookIsbn,
  String bookTitle
) implements QueryResult {
}
