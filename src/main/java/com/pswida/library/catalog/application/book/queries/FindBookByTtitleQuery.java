package com.pswida.library.catalog.application.book.queries;

import com.pswida.library.catalog.application.core.cqs.query.Query;

public record FindBookByTtitleQuery(String bookTitle) implements Query<FindBookByTtitleQueryResult> {

}
