package com.pswida.library.catalog.application.book.queries;

import com.pswida.library.common.application.cqs.query.QueryResult;
import com.pswida.library.catalog.domain.book.BookDiscussion;
import com.pswida.library.catalog.domain.book.BookIsbn;

public record FindBookByTtitleQueryResult(
  BookIsbn bookIsbn,
  String bookTitle,

  String discussionId,

  BookDiscussion.Status discussionStatus
) implements QueryResult {
}
