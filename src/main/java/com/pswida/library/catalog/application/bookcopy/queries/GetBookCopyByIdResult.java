package com.pswida.library.catalog.application.bookcopy.queries;

import com.pswida.library.catalog.domain.bookcopy.BookCopy;
import com.pswida.library.common.application.cqs.query.QueryResult;
import lombok.Builder;

@Builder
public record GetBookCopyByIdResult(
  String bookCopyId,
  BookCopy.Status status,
  String bookIsbn
) implements QueryResult {
}
