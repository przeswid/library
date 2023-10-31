package com.pswida.library.catalog.application.bookcopy.queries;

import com.pswida.library.common.application.cqs.query.Query;

public record GetBookCopyByIdQuery(String bookCopyId) implements Query<GetBookCopyByIdResult> {
}
